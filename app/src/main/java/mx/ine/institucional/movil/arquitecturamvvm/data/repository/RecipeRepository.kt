package mx.ine.institucional.movil.arquitecturamvvm.data.repository

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity

interface RecipeRepository {
    suspend fun getCategoriesList(): Resource<List<CategoryRecipe>>?
    suspend fun getMealListByCategoryName(nameMeal: String): Resource<List<Category>>?
    suspend fun getMealListById(idMeal: String): Resource<List<Meal>>?

    suspend fun saveFavoriteMeal(meal: MealEntity)
    suspend fun getAllFavoriteMeal(): Resource<List<Meal>>
    suspend fun deleteFavoriteMeal(meal: MealEntity): Resource<List<Meal>>
}