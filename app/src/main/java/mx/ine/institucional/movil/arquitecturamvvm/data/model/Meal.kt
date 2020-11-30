package mx.ine.institucional.movil.arquitecturamvvm.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(
    @SerializedName("idMeal")
    val idMeal: String = "",
    @SerializedName("strMeal")
    val nameMeal: String? = "",
    @SerializedName("strCategory")
    val categoryMeal: String? = "",
    @SerializedName("strMealThumb")
    val imageMealUrl: String? = "",
    @SerializedName("strYoutube")
    var videoMealUrl: String? = "",
    @SerializedName("strIngredient1")
    val ingredient1: String? = "",
    @SerializedName("strIngredient2")
    val ingredient2: String? = "",
    @SerializedName("strIngredient3")
    val ingredient3: String? = "",
    @SerializedName("strIngredient4")
    val ingredient4: String? = "",
    @SerializedName("strIngredient5")
    val ingredient5: String? = "",
    @SerializedName("strIngredient6")
    val ingredient6: String? = "",
    @SerializedName("strIngredient7")
    val ingredient7: String? = "",
    @SerializedName("strIngredient8")
    val ingredient8: String? = "",
    @SerializedName("strIngredient9")
    val ingredient9: String? = "",
    @SerializedName("strIngredient10")
    val ingredient10: String? = "",
    @SerializedName("strIngredient11")
    val ingredient11: String? = "",
    @SerializedName("strIngredient12")
    val ingredient12: String? = "",
    @SerializedName("strIngredient13")
    val ingredient13: String? = "",
    @SerializedName("strIngredient14")
    val ingredient14: String? = "",
    @SerializedName("strIngredient15")
    val ingredient15: String? = "",
    @SerializedName("strIngredient16")
    val ingredient16: String? = "",
    @SerializedName("strIngredient17")
    val ingredient17: String? = "",
    @SerializedName("strIngredient18")
    val ingredient18: String? = "",
    @SerializedName("strIngredient19")
    val ingredient19: String? = "",
    @SerializedName("strIngredient20")
    val ingredient20: String? = "",
    var saveIntoDB: Boolean = false

) : Parcelable

data class MealList(
    @SerializedName("meals")
    val mealList: List<Meal> = listOf()
)

@Entity(tableName = "meal_entity")
data class MealEntity(
    @PrimaryKey
    val idMeal: String,
    @ColumnInfo(name = "meal_strMeal")
    val nameMeal: String,
    @ColumnInfo(name = "meal_strCategory")
    val categoryMeal: String,
    @ColumnInfo(name = "meal_strMealThumb")
    val imageMealUrl: String,
    @ColumnInfo(name = "meal_strYoutube")
    val videoMealUrl: String,
    @ColumnInfo(name = "meal_strIngredient1")
    val ingredient1: String,
    @ColumnInfo(name = "meal_strIngredient2")
    val ingredient2: String,
    @ColumnInfo(name = "meal_strIngredient3")
    val ingredient3: String,
    @ColumnInfo(name = "meal_strIngredient4")
    val ingredient4: String,
    @ColumnInfo(name = "meal_strIngredient5")
    val ingredient5: String,
    @ColumnInfo(name = "meal_strIngredient6")
    val ingredient6: String,
    @ColumnInfo(name = "meal_strIngredient7")
    val ingredient7: String,
    @ColumnInfo(name = "meal_strIngredient8")
    val ingredient8: String,
    @ColumnInfo(name = "meal_strIngredient9")
    val ingredient9: String,
    @ColumnInfo(name = "meal_strIngredient10")
    val ingredient10: String,
    @ColumnInfo(name = "meal_strIngredient11")
    val ingredient11: String,
    @ColumnInfo(name = "meal_strIngredient12")
    val ingredient12: String,
    @ColumnInfo(name = "meal_strIngredient13")
    val ingredient13: String,
    @ColumnInfo(name = "meal_strIngredient14")
    val ingredient14: String,
    @ColumnInfo(name = "meal_strIngredient15")
    val ingredient15: String,
    @ColumnInfo(name = "meal_strIngredient16")
    val ingredient16: String,
    @ColumnInfo(name = "meal_strIngredient17")
    val ingredient17: String,
    @ColumnInfo(name = "meal_strIngredient18")
    val ingredient18: String,
    @ColumnInfo(name = "meal_strIngredient19")
    val ingredient19: String,
    @ColumnInfo(name = "meal_strIngredient20")
    val ingredient20: String,
    val saveIntoDB: Boolean = false

)

fun List<MealEntity>.asMealList(): MutableList<Meal> = this.map {
    Meal(
        it.idMeal,
        it.nameMeal,
        it.categoryMeal,
        it.imageMealUrl,
        it.videoMealUrl,
        it.ingredient1,
        it.ingredient2,
        it.ingredient3,
        it.ingredient4,
        it.ingredient5,
        it.ingredient6,
        it.ingredient7,
        it.ingredient8,
        it.ingredient9,
        it.ingredient10,
        it.ingredient11,
        it.ingredient12,
        it.ingredient13,
        it.ingredient14,
        it.ingredient15,
        it.ingredient16,
        it.ingredient17,
        it.ingredient18,
        it.ingredient19,
        it.ingredient20,
        it.saveIntoDB
    )
}.toMutableList()

fun Meal.asMealEntity(): MealEntity =
    MealEntity(
        this.idMeal,
        this.nameMeal!!,
        this.categoryMeal!!,
        this.imageMealUrl!!,
        this.videoMealUrl!!,
        this.ingredient1!!,
        this.ingredient2!!,
        this.ingredient3!!,
        this.ingredient4!!,
        this.ingredient5!!,
        this.ingredient6!!,
        this.ingredient7!!,
        this.ingredient8!!,
        this.ingredient9!!,
        this.ingredient10!!,
        this.ingredient11!!,
        this.ingredient12!!,
        this.ingredient13!!,
        this.ingredient14!!,
        this.ingredient15!!,
        this.ingredient16!!,
        this.ingredient17!!,
        this.ingredient18!!,
        this.ingredient19!!,
        this.ingredient20!!,
        this.saveIntoDB
    )