package mx.ine.institucional.movil.arquitecturamvvm.data.local

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import mx.ine.institucional.movil.arquitecturamvvm.data.model.asMealList
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val recipeDao: RecipeDao): LocalDataSource {

    override suspend fun saveFavoriteMealIntoRoom(meal: MealEntity) {
        recipeDao.saveFavorite(meal)
    }

    override suspend fun getAllFavoriteMealOfRoom(): Resource<List<Meal>> {
        return Resource.success(recipeDao.getAllMealsFavorites().asMealList())
    }

    override suspend fun deleteFavoriteMealOfRoom(meal: MealEntity) {
        recipeDao.deleteFavoriteMeal(meal)
    }
}