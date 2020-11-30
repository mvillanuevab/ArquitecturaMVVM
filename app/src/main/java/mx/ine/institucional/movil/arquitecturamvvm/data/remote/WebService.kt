package mx.ine.institucional.movil.arquitecturamvvm.data.remote

import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipeList
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealCategoryList
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryRecipeList?

    @GET("filter.php")
    suspend fun getMealsByNameCategory(@Query("c") nameCategory: String): MealCategoryList?

    @GET("lookup.php")
    suspend fun getMealFilterById(@Query("i") idMeal: String) : MealList?
}