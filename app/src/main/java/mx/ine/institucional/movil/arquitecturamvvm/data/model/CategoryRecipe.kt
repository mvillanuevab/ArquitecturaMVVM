package mx.ine.institucional.movil.arquitecturamvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryRecipe (
    @SerializedName("idCategory")
    val idCategory: String = "",
    @SerializedName("strCategory")
    val categoryName: String = "",
    @SerializedName("strCategoryThumb")
    val categoryImageUrl: String = ""
): Parcelable

data class CategoryRecipeList(
    @SerializedName("categories")
    val categoryRecipeList: List<CategoryRecipe> = listOf()
)