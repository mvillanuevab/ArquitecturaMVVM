package mx.ine.institucional.movil.arquitecturamvvm.view.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.utils.UtilFunctions
import mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel.MainViewModel


@AndroidEntryPoint
class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail) {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var meal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//detailArgs
        val safeArgs: RecipeDetailFragmentArgs by navArgs()
        meal = safeArgs.detailArgs
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadData(view)
    }

    private fun uploadData(view: View) {
        Glide.with(requireContext()).load(meal.imageMealUrl).into(iv_recipe_detail_image)
        tv_recipe_detail_title.text = meal.nameMeal

        val ingredients = UtilFunctions.getIngredients(meal)
        tv_recipe_detail_ingredients.text = ingredients

        if (meal.saveIntoDB) {
            ib_recipe_detail_save_favorite.visibility = View.GONE
        } else {
            ib_recipe_detail_save_favorite.visibility = View.VISIBLE
            ib_recipe_detail_save_favorite.setOnClickListener {
                viewModel.saveMealFavorite(UtilFunctions.getObjectMealEntity(meal))
                Snackbar.make(view, "added to favorites", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

//        btn_watch_video.setOnClickListener {
//            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(meal.videoMealUrl))
//            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(meal.videoMealUrl))
//            try {
//                requireContext().startActivity(appIntent)
//            } catch (activity: ActivityNotFoundException) {
//                requireContext().startActivity(webIntent)
//            }
//        }
    }
}