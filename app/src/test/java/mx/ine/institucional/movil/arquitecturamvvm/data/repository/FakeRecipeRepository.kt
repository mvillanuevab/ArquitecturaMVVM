package mx.ine.institucional.movil.arquitecturamvvm.data.repository

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.*

class FakeRecipeRepository: RecipeRepository {

    private val categoryRecipe = mutableListOf<CategoryRecipe>(
        CategoryRecipe("1", "cat1", "image1"),
        CategoryRecipe("2", "cat2", "image2"),
        CategoryRecipe("3", "cat3", "image3"),
        CategoryRecipe("4", "cat4", "image4"),
        CategoryRecipe("5", "cat5", "image5")
    )
    private val categoryRecipe2 = mutableListOf<CategoryRecipe>()

    private val category = mutableListOf<Category>(
        Category("1", "cat1", "image1"),
        Category("2", "cat1", "image2"),
        Category("3", "cat1", "image3"),
        Category("4", "cat1", "image4"),
        Category("5", "cat1", "image5")
    )

    private val mealList = listOf(
        Meal(
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
            false
        )
    )

    private val mealItem = mutableListOf<MealEntity>()

    fun getCategoriesListEmpty(): Resource<List<CategoryRecipe>>? {
        return try {
            if(categoryRecipe2.isNotEmpty()) {
                Resource.success(categoryRecipe2)
            } else {
                Resource.error("unknown error check your internet connection", null)
            }
        } catch (e: Exception) {
            Resource.error("unknown error check your internet connection", null)
        }
    }

    override suspend fun getCategoriesList(): Resource<List<CategoryRecipe>>? {
        return try {
            if(categoryRecipe.isNotEmpty()) {
                Resource.success(categoryRecipe)
            } else {
                Resource.error("", null)
            }
        } catch (e: Exception) {
            Resource.error("", null)
        }
    }

    override suspend fun getMealListByCategoryName(nameMeal: String): Resource<List<Category>>? {
        return try {
            return if (nameMeal == "cat1"){
                Resource.success(category)
            } else {
                Resource.error("Error category not found", null)
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString(), null)
        }
    }

    override suspend fun getMealListById(idMeal: String): Resource<List<Meal>>? {
        return try {
            return if (idMeal == "2") {
                 Resource.success(mealList)
            } else {
                Resource.error("Item with id: $idMeal not found", null)
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString(), null)
        }
    }

    override suspend fun saveFavoriteMeal(meal: MealEntity) {
        mealItem.add(meal)
    }

    override suspend fun getAllFavoriteMeal(): Resource<List<Meal>> {
        return try {
            if (mealItem.asMealList().isNotEmpty()) {
                Resource.success(mealItem.asMealList())
            } else {
                Resource.error("No hay datos en la base de datos", null)
            }
        } catch (e: Exception) {
            Resource.error("Error desconocido puede que no haya datos en la bdd", null)
        }

    }

    override suspend fun deleteFavoriteMeal(meal: MealEntity): Resource<List<Meal>> {
        mealItem.remove(meal)
        return getAllFavoriteMeal()
    }
}