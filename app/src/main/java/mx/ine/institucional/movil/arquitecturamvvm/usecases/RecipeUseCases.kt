package mx.ine.institucional.movil.arquitecturamvvm.usecases

import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import mx.ine.institucional.movil.arquitecturamvvm.data.repository.RecipeRepository
import javax.inject.Inject

class RecipeUseCases @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend fun getCategoriesList(): Resource<List<CategoryRecipe>>? {
        return recipeRepository.getCategoriesList()
    }

    suspend fun getMealListByCategoryName(nameMeal: String): Resource<List<Category>> {
        return recipeRepository.getMealListByCategoryName(nameMeal)!!
    }

    suspend fun getMealListById(idMeal: String): Resource<List<Meal>> {
        return recipeRepository.getMealListById(idMeal)!!
    }

    suspend fun saveFavoriteMeal(meal: MealEntity) {
        recipeRepository.saveFavoriteMeal(meal)
    }

    suspend fun getAllFavoriteMeal(): Resource<List<Meal>> {
        return recipeRepository.getAllFavoriteMeal()
    }

    suspend fun deleteFavoriteMeal(meal: MealEntity): Resource<List<Meal>> {
        return recipeRepository.deleteFavoriteMeal(meal)
    }
}