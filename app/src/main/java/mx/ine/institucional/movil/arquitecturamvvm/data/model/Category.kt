package mx.ine.institucional.movil.arquitecturamvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Category (
    @SerializedName("idMeal")
    val idMealCategory: String = "",
    @SerializedName("strMeal")
    val nameCategory: String = "",
    @SerializedName("strMealThumb")
    val imgMealCategoryUrl: String = ""
): Parcelable

data class MealCategoryList(
    @SerializedName("meals")
    val mealCategoryList: List<Category> = listOf()
)