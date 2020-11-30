package mx.ine.institucional.movil.arquitecturamvvm.utils

import com.google.common.truth.Truth.assertThat
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import org.junit.Before
import org.junit.Test

class UtilFunctionsTest {

    private lateinit var mealNullIngredientsAndVideoUrl: Meal
    private lateinit var meal: Meal

    @Before
    fun setup() {
        mealNullIngredientsAndVideoUrl = Meal(
            "1",
            "Beef and Mustard",
            "Beff",
            "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
            null,
            "ingredient1",
            "ingredient2",
            "ingredient3",
            "ingredient4",
            "ingredient5",
            "ingredient6",
            "ingredient7",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            false
        )

        meal = Meal(
            "2",
            "Beef and Mustard",
            "Beff",
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
    }

    @Test
    fun transformNullValuesTest_transformObject_returnNewObjectWithStringValues() {
        val newMeal = UtilFunctions.transformObject(mealNullIngredientsAndVideoUrl)
        assertThat(newMeal).isNotEqualTo(mealNullIngredientsAndVideoUrl)
    }

    @Test
    fun getIngredientsTest_getIngredients_returnString() {
        val stringGetIngredients = UtilFunctions.getIngredients(meal)
        val stringIngredients = "${meal.ingredient1}\n${meal.ingredient4}\n${meal.ingredient6}\n${meal.ingredient9}\n${meal.ingredient14}"
        assertThat(stringGetIngredients).isEqualTo(stringIngredients)
    }

    @Test
    fun convertMeal_getObjectMealEntity_returnMealEntityObject(){
        val mealEntity = UtilFunctions.getObjectMealEntity(meal)
        val objMeal = MealEntity(
            "2",
            "Beef and Mustard",
            "Beff",
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
        assertThat(mealEntity).isEqualTo(objMeal)
    }
}