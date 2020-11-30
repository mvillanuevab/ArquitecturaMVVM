package mx.ine.institucional.movil.arquitecturamvvm.data.repository

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.local.LocalDataSource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import mx.ine.institucional.movil.arquitecturamvvm.data.remote.RemoteDataSource
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteRemoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): RecipeRepository {

    override suspend fun getCategoriesList(): Resource<List<CategoryRecipe>> {
        return remoteRemoteDataSource.getAllCategories()!!
    }

    override suspend fun getMealListByCategoryName(nameMeal: String): Resource<List<Category>> {
        return remoteRemoteDataSource.getMealByCategoryName(nameMeal)!!
    }

    override suspend fun getMealListById(idMeal: String): Resource<List<Meal>> {
        return remoteRemoteDataSource.getMealFilterById(idMeal)!!
    }

    override suspend fun saveFavoriteMeal(meal: MealEntity) {
        localDataSource.saveFavoriteMealIntoRoom(meal)
    }

    override suspend fun getAllFavoriteMeal(): Resource<List<Meal>> {
        return localDataSource.getAllFavoriteMealOfRoom()
    }

    override suspend fun deleteFavoriteMeal(meal: MealEntity): Resource<List<Meal>> {
        localDataSource.deleteFavoriteMealOfRoom(meal)
        return getAllFavoriteMeal()
    }

}