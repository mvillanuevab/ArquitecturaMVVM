package mx.ine.institucional.movil.arquitecturamvvm.view.adapters.favorite

import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity

interface FavoriteListener {
    fun onItemFavoriteClick(meal: Meal, position: Int)
    fun onItemDeleteFavoriteClick(meal: MealEntity, position: Int)
}