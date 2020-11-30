package mx.ine.institucional.movil.arquitecturamvvm.view.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_favorite.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.base.Status
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.favorite.FavoriteAdapter
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.favorite.FavoriteListener
import mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel.MainViewModel


@AndroidEntryPoint
class RecipeFavoriteFragment : Fragment(R.layout.fragment_recipe_favorite), FavoriteListener {

    private lateinit var adapter: FavoriteAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoriteAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupRecyclerView() {
        rv_recipe_favorites.layoutManager = LinearLayoutManager(requireContext())
        rv_recipe_favorites.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getAllFavorites().observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let { result ->
                when (result?.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        adapter.setFavoriteList(result.data!!)
                    }
                    Status.ERROR -> {
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

    override fun onItemFavoriteClick(meal: Meal, position: Int) {
        val action = RecipeFavoriteFragmentDirections.actionRecipeFavoriteFragmentToRecipeDetailFragment(meal)
        findNavController().navigate(action)
    }

    override fun onItemDeleteFavoriteClick(meal: MealEntity, position: Int) {
        viewModel.deleteMealFavorite(meal).observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {result ->
                when (result.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        Toast.makeText(requireContext(), "Recipe deleted!", Toast.LENGTH_SHORT).show()
                        adapter.setFavoriteList(result.data!!)
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            "Error occurred connecting to the server!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}