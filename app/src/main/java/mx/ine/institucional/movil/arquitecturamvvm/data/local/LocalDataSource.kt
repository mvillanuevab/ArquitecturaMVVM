package mx.ine.institucional.movil.arquitecturamvvm.data.local

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity

interface LocalDataSource {
    suspend fun saveFavoriteMealIntoRoom(meal: MealEntity)
    suspend fun getAllFavoriteMealOfRoom(): Resource<List<Meal>>
    suspend fun deleteFavoriteMealOfRoom(meal: MealEntity)
}