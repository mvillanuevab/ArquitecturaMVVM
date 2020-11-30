package mx.ine.institucional.movil.arquitecturamvvm.view.adapters.main

import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe

interface MainListener {
    fun onCategoryClick(categoryRecipe: CategoryRecipe, position: Int)
}