package mx.ine.institucional.movil.arquitecturamvvm.data.local

import androidx.room.*
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM meal_entity")
    suspend fun getAllMealsFavorites(): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(meal: MealEntity)

    @Delete
    suspend fun deleteFavoriteMeal(meal: MealEntity)

}