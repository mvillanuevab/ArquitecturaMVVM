package mx.ine.institucional.movil.arquitecturamvvm.view.adapters.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_recipe_favorite.view.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.base.BaseViewHolder
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.asMealEntity

class FavoriteAdapter(
    private val context: Context,
    private val itemClickListener: FavoriteListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var mealList = listOf<Meal>()

    fun setFavoriteList(favoriteList: List<Meal>){
        this.mealList = favoriteList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row_recipe_favorite, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(mealList[position], position)
        }
    }

    override fun getItemCount(): Int = mealList.size

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Meal>(itemView) {
        override fun bind(item: Meal, position: Int) {
            itemView.tv_title_recipe_favorite.text = item.nameMeal
            Glide.with(context).load(item.imageMealUrl).into(itemView.iv_recipe_favorite)
            itemView.ib_delete_recipe_favorite.setOnClickListener {
                itemClickListener.onItemDeleteFavoriteClick(item.asMealEntity(), position)
            }
            itemView.setOnClickListener {
                itemClickListener.onItemFavoriteClick(item, position)
            }
        }

    }
}