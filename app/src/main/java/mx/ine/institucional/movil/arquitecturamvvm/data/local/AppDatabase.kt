package mx.ine.institucional.movil.arquitecturamvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}