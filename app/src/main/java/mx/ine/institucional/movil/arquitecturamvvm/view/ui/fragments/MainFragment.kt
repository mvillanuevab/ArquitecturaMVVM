package mx.ine.institucional.movil.arquitecturamvvm.view.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import mx.ine.institucional.movil.arquitecturamvvm.R
import mx.ine.institucional.movil.arquitecturamvvm.base.Status
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.main.MainAdapter
import mx.ine.institucional.movil.arquitecturamvvm.view.adapters.main.MainListener
import mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MainListener {

    val viewModel by activityViewModels<MainViewModel>()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        adapter = MainAdapter( //new
            requireContext(),
            listOf(),
            this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        rv_main_recipes.layoutManager = GridLayoutManager(requireContext(), 2)
        rv_main_recipes.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.fetchCategoryListRecipe.observe(
            viewLifecycleOwner, Observer { result ->
                result?.getContentIfNotHandled()?.let { res ->
                    when (res.status) {
                        Status.LOADING -> {
                            rl_main_recipes.visibility = View.VISIBLE
                            empty_container_main.visibility = View.GONE
                        }
                        Status.SUCCESS -> {
                            if (res.data.isNullOrEmpty()) {
                                rl_main_recipes.visibility = View.VISIBLE
                                return@Observer
                            }
                            adapter = MainAdapter(
                                requireContext(),
                                res.data,
                                this
                            )
                            rv_main_recipes.adapter = adapter
                            empty_container_main.visibility = View.GONE
                            rl_main_recipes.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            rl_main_recipes.visibility = View.GONE
                            empty_container_main.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(),
                                "Error occurred connecting to the server ${res.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        )
    }


    override fun onCategoryClick(categoryRecipe: CategoryRecipe, position: Int) {
        val action = MainFragmentDirections.actionMainFragmentToRecipeFragment(categoryRecipe)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_goto_favorite -> {
                findNavController().navigate(R.id.action_mainFragment_to_recipeFavoriteFragment)
                false
            }
            else -> false
        }
    }
}