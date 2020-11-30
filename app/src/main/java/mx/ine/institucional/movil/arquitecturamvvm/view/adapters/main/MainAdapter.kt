package mx.ine.institucional.movil.arquitecturamvvm.view.adapters.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_main.view.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.base.BaseViewHolder
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.main.MainListener

class MainAdapter(
    private val context: Context,
    private val categoryList: List<CategoryRecipe>,
    private val itemClickListener: MainListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(categoryList[position], position)
        }
    }

    override fun getItemCount(): Int = categoryList.size

    inner class MainViewHolder(itemView: View): BaseViewHolder<CategoryRecipe>(itemView) {
        override fun bind(item: CategoryRecipe, position: Int) {
            Glide.with(context).load(item.categoryImageUrl).into(itemView.iv_main_recipe)
            itemView.tv_main_title_recipe.text = item.categoryName
            itemView.setOnClickListener {
                itemClickListener.onCategoryClick(item, position)
            }
        }

    }

}