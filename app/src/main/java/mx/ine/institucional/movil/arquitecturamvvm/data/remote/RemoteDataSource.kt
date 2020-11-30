package mx.ine.institucional.movil.arquitecturamvvm.data.remote

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal

interface RemoteDataSource {
    suspend fun getAllCategories(): Resource<List<CategoryRecipe>>?
    suspend fun getMealByCategoryName(meal: String): Resource<List<Category>>?
    suspend fun getMealFilterById(idMeal: String): Resource<List<Meal>>?
}