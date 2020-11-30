package mx.ine.institucional.movil.arquitecturamvvm.view.adapters.recipe

import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category

interface RecipeListener {
    fun onMealClick(category: Category, position: Int)
}