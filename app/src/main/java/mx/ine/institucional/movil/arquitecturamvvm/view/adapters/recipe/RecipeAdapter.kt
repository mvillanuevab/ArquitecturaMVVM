package mx.ine.institucional.movil.arquitecturamvvm.view.adapters.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_recipe.view.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.base.BaseViewHolder
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.recipe.RecipeListener

class RecipeAdapter(
    private val context: Context,
    private val itemClickListener: RecipeListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var mealList = listOf<Category>()


    fun setCategoryList(categoryList: List<Category>) {
        this.mealList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(mealList[position], position)
        }
    }

    override fun getItemCount(): Int = mealList.size

    inner class MainViewHolder(itemView: View): BaseViewHolder<Category>(itemView) {
        override fun bind(item: Category, position: Int) {
            itemView.tv_recipe_title.text = item.nameCategory
            Glide.with(context).load(item.imgMealCategoryUrl).into(itemView.iv_recipe_food)
            itemView.setOnClickListener {
                itemClickListener.onMealClick(item, position)
            }
        }

    }
}