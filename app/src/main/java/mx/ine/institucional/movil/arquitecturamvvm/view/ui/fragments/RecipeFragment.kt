package mx.ine.institucional.movil.arquitecturamvvm.view.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.base.Status
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.utils.UtilFunctions
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.recipe.RecipeAdapter
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.recipe.RecipeListener
import mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel.MainViewModel


@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe), RecipeListener {

    private lateinit var adapter: RecipeAdapter
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var category: CategoryRecipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val safeArgs: RecipeFragmentArgs by navArgs()
        category = safeArgs.recipeArgs
        adapter = RecipeAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver(category.categoryName)
    }

    private fun setupRecyclerView() {
        rv_recipe_foods_cat.layoutManager = LinearLayoutManager(requireContext())
        rv_recipe_foods_cat.adapter = adapter
    }

    private fun setupObserver(search: String) {
        viewModel.fetchCategoriesByName(search).observe(
            viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { result ->
                    when (result.status) {
                        Status.LOADING -> {
                            rl_recipe_foods_cat.visibility = View.VISIBLE
                            empty_container_recipe.visibility = View.GONE
                        }
                        Status.SUCCESS -> {
                            rl_recipe_foods_cat.visibility = View.GONE
                            if (result.data.isNullOrEmpty()) {
                                rl_recipe_foods_cat.visibility = View.GONE
                                empty_container_recipe.visibility = View.VISIBLE
                                return@Observer
                            }
                            adapter.setCategoryList(result.data)
                            empty_container_recipe.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            rl_recipe_foods_cat.visibility = View.GONE
                            empty_container_recipe.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(),
                                "Error occurred connecting to the server ${result.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            })
    }

//    private fun searchByCategory(category: String) {
//        viewModel.setMealCategoryName(category)
//    }

    override fun onMealClick(category: Category, position: Int) {
        viewModel.fetchMealById(category).observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.LOADING -> {
                        rl_recipe_foods_cat.visibility = View.VISIBLE
                        empty_container_recipe.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        rl_recipe_foods_cat.visibility = View.GONE
                        if (result.data.isNullOrEmpty()) {
                            rl_recipe_foods_cat.visibility = View.GONE
                            empty_container_recipe.visibility = View.VISIBLE
                            return@Observer
                        }
                        empty_container_recipe.visibility = View.GONE
                        val meal = UtilFunctions.transformObject(result.data[0])
                        val action =
                            RecipeFragmentDirections.actionRecipeFragmentToRecipeDetailFragment(meal)
                        findNavController().navigate(action)
                    }
                    Status.ERROR -> {
                        rl_recipe_foods_cat.visibility = View.GONE
                        empty_container_recipe.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}