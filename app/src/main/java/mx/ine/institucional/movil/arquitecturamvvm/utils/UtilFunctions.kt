package mx.ine.institucional.movil.arquitecturamvvm.utils

import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity

object UtilFunctions {
    private lateinit var mealMejorado: Meal

    fun transformObject(meal: Meal) : Meal {
        val mealArray = arrayListOf(meal)
        mealArray.map {
            mealMejorado = Meal(
                it.idMeal,
                it.nameMeal ?: "",
                it.categoryMeal ?: "",
                it.imageMealUrl ?: "",
                it.videoMealUrl ?: "",
                it.ingredient1 ?: "",
                it.ingredient2 ?: "",
                it.ingredient3 ?: "",
                it.ingredient4 ?: "",
                it.ingredient5 ?: "",
                it.ingredient6 ?: "",
                it.ingredient7 ?: "",
                it.ingredient8 ?: "",
                it.ingredient9 ?: "",
                it.ingredient10 ?: "",
                it.ingredient11 ?: "",
                it.ingredient12 ?: "",
                it.ingredient13 ?: "",
                it.ingredient14 ?: "",
                it.ingredient15 ?: "",
                it.ingredient16 ?: "",
                it.ingredient17 ?: "",
                it.ingredient18 ?: "",
                it.ingredient19 ?: "",
                it.ingredient20 ?: "",
                it.saveIntoDB
            )
        }

        if (mealMejorado.videoMealUrl.equals(""))
            mealMejorado.videoMealUrl = "https://www.youtube.com/"
        return mealMejorado

    }

        //used in RecipeDetailFragment
        fun getIngredients(meal: Meal): String {
            var ingredients = ""
            val mealIngredientsArrayString = arrayListOf(
                meal.ingredient1,
                meal.ingredient2,
                meal.ingredient3,
                meal.ingredient4,
                meal.ingredient5,
                meal.ingredient6,
                meal.ingredient7,
                meal.ingredient8,
                meal.ingredient9,
                meal.ingredient10,
                meal.ingredient11,
                meal.ingredient12,
                meal.ingredient13,
                meal.ingredient14,
                meal.ingredient15,
                meal.ingredient16,
                meal.ingredient17,
                meal.ingredient18,
                meal.ingredient19,
                meal.ingredient20
            ).filterNotNull().filter { it.isNotEmpty() }

            for ((index, value) in mealIngredientsArrayString.withIndex()){
                ingredients += if (index < mealIngredientsArrayString.size-1) {
                    (value + "\n")
                }else {
                    value
                }
            }
            return ingredients
        }

        //used in RecipeDetailFragment & RecipeFavoriteFragment
        fun getObjectMealEntity(meal: Meal): MealEntity {
            meal.saveIntoDB = true
            return MealEntity(
                meal.idMeal,
                meal.nameMeal!!,
                meal.categoryMeal!!,
                meal.imageMealUrl!!,
                meal.videoMealUrl!!,
                meal.ingredient1!!,
                meal.ingredient2!!,
                meal.ingredient3!!,
                meal.ingredient4!!,
                meal.ingredient5!!,
                meal.ingredient6!!,
                meal.ingredient7!!,
                meal.ingredient8!!,
                meal.ingredient9!!,
                meal.ingredient10!!,
                meal.ingredient11!!,
                meal.ingredient12!!,
                meal.ingredient13!!,
                meal.ingredient14!!,
                meal.ingredient15!!,
                meal.ingredient16!!,
                meal.ingredient17!!,
                meal.ingredient18!!,
                meal.ingredient19!!,
                meal.ingredient20!!,
                meal.saveIntoDB
            )
        }
//    }
}