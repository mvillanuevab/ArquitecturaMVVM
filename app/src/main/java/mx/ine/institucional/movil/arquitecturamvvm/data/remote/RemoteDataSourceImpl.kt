package mx.ine.institucional.movil.arquitecturamvvm.data.remote

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val webService: WebService
) : RemoteDataSource {

    override suspend fun getAllCategories(): Resource<List<CategoryRecipe>> {
        return Resource.success(webService.getAllCategories()?.categoryRecipeList ?: listOf())
    }
    override suspend fun getMealByCategoryName(meal: String): Resource<List<Category>> {
        return Resource.success(
            webService.getMealsByNameCategory(meal)?.mealCategoryList ?: listOf()
        )
    }
    override suspend fun getMealFilterById(idMeal: String): Resource<List<Meal>> {
        return Resource.success(webService.getMealFilterById(idMeal)?.mealList ?: listOf())
    }
}