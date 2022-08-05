package com.absut.isro.archive.ui.spacecraft

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.databinding.FragmentSpacecraftBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.adapter.SpacecraftAdapter
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.Resource
import com.absut.isro.archive.utils.State
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class SpacecraftFragment : Fragment() {

    private var _binding: FragmentSpacecraftBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ISROViewModel
    private lateinit var adapterSpacecraft: SpacecraftAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpacecraftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        initViews()
        getSpacecrafts()
        collectSpacecrafts()

    }

    private fun initViews() {
        adapterSpacecraft = SpacecraftAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterSpacecraft
            itemAnimator = DefaultItemAnimator()
        }
    }


    private fun collectSpacecrafts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.spacecrafts.collect { state ->
                    when (state) {
                        is State.Error -> {
                            binding.emptyState.visibility = View.GONE
                            hideProgress()
                            Snackbar.make(binding.recyclerView, state.message, Snackbar.LENGTH_SHORT).show()
                        }
                        is State.Loading -> {
                            binding.emptyState.visibility = View.GONE
                            showProgress()
                        }
                        is State.Success -> {
                            if (state.data.isNotEmpty()) {
                                adapterSpacecraft.submitList(state.data.toMutableList())
                                binding.emptyState.visibility = View.GONE
                                hideProgress()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getSpacecrafts() = viewModel.getSpacecrafts()


/*
    private fun getSpacecrafts() {
        viewModel.getSpacecrafts()
        viewModel.spacecrafts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.emptyState.visibility = View.GONE
                    hideProgress()
                    it.data?.let { spacecraftList ->
                        adapterSpacecraft.submitList(spacecraftList.spacecrafts.toList())
                    }
                }
                is Resource.Loading -> {
                    binding.emptyState.visibility = View.GONE
                    showProgress()
                }
                is Resource.Error -> {
                    binding.emptyState.visibility = View.GONE
                    hideProgress()
                    Snackbar.make(
                        binding.recyclerView,
                        it.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Resource.NoConnection -> {
                    binding.emptyState.visibility = View.VISIBLE
                    binding.btRetry.setOnClickListener {
                        viewModel.getSpacecrafts()
                    }
                }
            }
        }

    }
*/


    private fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    /*  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
          inflater.inflate(R.menu.layout_menu, menu)

          val layoutButton = menu.findItem(R.id.action_switch_layout)
          setIcon(layoutButton)
      }

      /**
       * Sets the LayoutManager for the [RecyclerView] based on the desired orientation of the list.
       *
       * Notice that because the enclosing class has changed from an Activity to a Fragment,
       * the signature of the LayoutManagers has to slightly change.
       */
      private fun chooseLayout() {
          if (isLinearLayoutManager) {
              recyclerView.layoutManager = LinearLayoutManager(context)
          } else {
              recyclerView.layoutManager = GridLayoutManager(context, 4)
          }
          recyclerView.adapter = LetterAdapter()
      }

      private fun setIcon(menuItem: MenuItem?) {
          if (menuItem == null)
              return

          menuItem.icon =
              if (isLinearLayoutManager)
                  ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
              else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
      }

      /**
       * Determines how to handle interactions with the selected [MenuItem]
       */
      override fun onOptionsItemSelected(item: MenuItem): Boolean {
          return when (item.itemId) {
              R.id.action_switch_layout -> {
                  // Sets isLinearLayoutManager (a Boolean) to the opposite value
                  isLinearLayoutManager = !isLinearLayoutManager
                  // Sets layout and icon
                  chooseLayout()
                  setIcon(item)

                  return true
              }
              // Otherwise, do nothing and use the core event handling

              // when clauses require that all possible paths be accounted for explicitly,
              // for instance both the true and false cases if the value is a Boolean,
              // or an else to catch all unhandled cases.
              else -> super.onOptionsItemSelected(item)
          }
      }*/

}