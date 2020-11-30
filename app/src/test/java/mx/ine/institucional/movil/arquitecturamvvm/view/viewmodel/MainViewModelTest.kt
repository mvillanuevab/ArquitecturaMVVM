package mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.IdlingRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import mx.ine.institucional.movil.arquitecturamvvm.base.Status
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import mx.ine.institucional.movil.arquitecturamvvm.data.repository.FakeRecipeRepository
import mx.ine.institucional.movil.arquitecturamvvm.usecases.RecipeUseCases
import mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel.MainViewModel
import mx.ine.institucional.movil.recipesarquitecturamvvm.MainCoroutineRule
import mx.ine.institucional.movil.recipesarquitecturamvvm.getOrAwaitValueTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instanTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private val fakeRecipeRepository = FakeRecipeRepository()
    private val recipeUseCases = RecipeUseCases(fakeRecipeRepository)

    @Before
    fun setup() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        viewModel = MainViewModel(recipeUseCases, mainCoroutineRule.dispatcher)
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//        if (EspressoIdlingResource.countingIdlingResource.isIdleNow) {
//            IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//            IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
//        }

    }

    @After
    fun cleanUp() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun `insert favorite into room return success`() = mainCoroutineRule.runBlockingTest {

        viewModel.saveMealFavorite(
            MealEntity(
                "2",
                "Beef and Mustard",
                "cat1",
                "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
                "https://youtube.com/",
                "ingredient1",
                "",
                "",
                "ingredient4",
                "",
                "ingredient6",
                "",
                "",
                "ingredient9",
                "",
                "",
                "",
                "",
                "ingredient14",
                "",
                "",
                "",
                "",
                "",
                "",
                true
            )
        )
        val value = viewModel.insertFavoriteItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `insert favorite into room with data important empty return error`() = mainCoroutineRule.runBlockingTest  {

        viewModel.saveMealFavorite(
            MealEntity(
                "",
                "",
                "",
                "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
                "https://youtube.com/",
                "ingredient1",
                "",
                "",
                "ingredient4",
                "",
                "ingredient6",
                "",
                "",
                "ingredient9",
                "",
                "",
                "",
                "",
                "ingredient14",
                "",
                "",
                "",
                "",
                "",
                "",
                true
            )
        )
        val value = viewModel.insertFavoriteItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `get all favorites of the room db return success`() = mainCoroutineRule.runBlockingTest {

        val mealEntity = MealEntity(
            "2",
            "Beef and Mustard",
            "cat1",
            "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
            "https://youtube.com/",
            "ingredient1",
            "",
            "",
            "ingredient4",
            "",
            "ingredient6",
            "",
            "",
            "ingredient9",
            "",
            "",
            "",
            "",
            "ingredient14",
            "",
            "",
            "",
            "",
            "",
            "",
            true
        )

        viewModel.saveMealFavorite(mealEntity)

        viewModel.getAllFavorites().getOrAwaitValueTest()

        val valueGetData = viewModel.getFavoritesItemData.getOrAwaitValueTest().getContentIfNotHandled()
        assertThat(valueGetData?.status).isEqualTo(Status.SUCCESS)
        assertThat(valueGetData?.data?.size).isEqualTo(1)
        assertThat(valueGetData?.message).isEqualTo(null)
    }

    @Test
    fun `get all favorites of the room db isEmpty return Error`() = mainCoroutineRule.runBlockingTest {
        viewModel.getAllFavorites().getOrAwaitValueTest()

        val valueGetData = viewModel.getFavoritesItemData.getOrAwaitValueTest()
        assertThat(valueGetData.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
        assertThat(valueGetData.hasBeenHandled).isEqualTo(true)
    }


    @Test
    fun `delete favorite of room return Success and 1 `()  = mainCoroutineRule.runBlockingTest {

        val mealEntity1 =
            MealEntity(
                "2",
                "Beef and Mustard",
                "cat1",
                "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
                "https://youtube.com/",
                "ingredient1",
                "",
                "",
                "ingredient4",
                "",
                "ingredient6",
                "",
                "",
                "ingredient9",
                "",
                "",
                "",
                "",
                "ingredient14",
                "",
                "",
                "",
                "",
                "",
                "",
                true
            )

        val mealEntity2 =
            MealEntity(
                "2",
                "Beef and Mustard",
                "cat1",
                "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
                "https://youtube.com/",
                "ingredient1",
                "",
                "",
                "ingredient4",
                "",
                "ingredient6",
                "",
                "",
                "ingredient9",
                "",
                "",
                "",
                "",
                "ingredient14",
                "",
                "",
                "",
                "",
                "",
                "",
                true
            )

        viewModel.saveMealFavoriteIntoDb(mealEntity1)
        viewModel.saveMealFavoriteIntoDb(mealEntity2)

        viewModel.deleteMealFavorite(mealEntity1).getOrAwaitValueTest()

        val response = viewModel.getUpdateFavoritesData.getOrAwaitValueTest()
        val res = response.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.SUCCESS)
        assertThat(res?.data?.size).isEqualTo(1)
        assertThat(response.hasBeenHandled).isEqualTo(true)

    }

    @Test
    fun `delete favorite of roomDB is dataEmpty return Error and message  `()  = mainCoroutineRule.runBlockingTest {

        val mealEntity1 =
            MealEntity(
                "2",
                "Beef and Mustard",
                "cat1",
                "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
                "https://youtube.com/",
                "ingredient1",
                "",
                "",
                "ingredient4",
                "",
                "ingredient6",
                "",
                "",
                "ingredient9",
                "",
                "",
                "",
                "",
                "ingredient14",
                "",
                "",
                "",
                "",
                "",
                "",
                true
            )


        viewModel.saveMealFavoriteIntoDb(mealEntity1)

        viewModel.deleteMealFavorite(mealEntity1).getOrAwaitValueTest()

        val response = viewModel.getUpdateFavoritesData.getOrAwaitValueTest()
        val res = response.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.ERROR)
        assertThat(res?.message).isEqualTo("No hay datos en la base de datos")
        assertThat(response.hasBeenHandled).isEqualTo(true)
    }

    @Test
    fun `get category recipe is not empty return succes && 5 items`() = mainCoroutineRule.runBlockingTest {

        viewModel.fetchCategoryListRecipe.getOrAwaitValueTest()

        val result = viewModel.getCategoryListRecipe.getOrAwaitValueTest()
        val res = result.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.SUCCESS)
        assertThat(res?.data?.size).isEqualTo(5)
    }

    @Test
    fun `get category recipe isEmpty return Error && message`() = mainCoroutineRule.runBlockingTest {

        val result = fakeRecipeRepository.getCategoriesListEmpty()

        assertThat(result?.status).isEqualTo(Status.ERROR)
        assertThat(result?.message).isEqualTo("unknown error check your internet connection")
    }

    @Test
    fun `get categories by name return success && 5 items`() = mainCoroutineRule.runBlockingTest {
        viewModel.fetchCategoriesByName("cat1").getOrAwaitValueTest()

        val result = viewModel.getCategoriesByName.getOrAwaitValueTest()
        val res = result.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.SUCCESS)
        assertThat(res?.data?.size).isEqualTo(5)
    }

    @Test
    fun `get categories by name is Category not exist return Error && message`() = mainCoroutineRule.runBlockingTest {
        viewModel.fetchCategoriesByName("cat2").getOrAwaitValueTest()

        val result = viewModel.getCategoriesByName.getOrAwaitValueTest()
        val res = result.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.ERROR)
        assertThat(res?.message).isEqualTo("Error category not found")
    }

    @Test
    fun `get meal by id return success && 1 item`() = mainCoroutineRule.runBlockingTest {
        val objCategory = Category("2", "cat1", "image2")
        viewModel.fetchMealById(objCategory).getOrAwaitValueTest()

        val result = viewModel.getMealById.getOrAwaitValueTest()
        val res = result.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.SUCCESS)
        assertThat(res?.data?.size).isEqualTo(1)
    }

    @Test
    fun `get meal by id is ID not exist return Error && message`() = mainCoroutineRule.runBlockingTest {
        val objCategory = Category("3", "cat1", "image3")
        viewModel.fetchMealById(objCategory).getOrAwaitValueTest()

        val result = viewModel.getMealById.getOrAwaitValueTest()
        val res = result.getContentIfNotHandled()

        assertThat(res?.status).isEqualTo(Status.ERROR)
        assertThat(res?.message).isEqualTo("Item with id: 3 not found")
    }

}