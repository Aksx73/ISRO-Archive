package com.absut.isro.archive.ui.screens

import android.os.Bundle
import android.view.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.ui.components.SpacecraftListItem
import com.absut.isro.archive.ui.ISROViewModel
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme


class SpacecraftFragment : Fragment() {

    /*private var _binding: FragmentSpacecraftBinding? = null
    private val binding get() = _binding!!*/

    private val viewModel: ISROViewModel by activityViewModels()
    //private lateinit var adapterSpacecraft: SpacecraftAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /* _binding = FragmentSpacecraftBinding.inflate(inflater, container, false)
         return binding.root*/

        viewModel.getSpacecrafts()
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    SpacecraftScreen()
                }
            }
        }
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
                            Snackbar.make(
                                binding.recyclerView,
                                state.message,
                                Snackbar.LENGTH_SHORT
                            ).show()
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


    private fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressCircular.visibility = View.INVISIBLE
    }*/

    override fun onDestroy() {
        super.onDestroy()
       // _binding = null
    }

    @Composable
    fun SpacecraftScreen(modifier: Modifier = Modifier) {
        val spacecrafts = viewModel.spacecrafts

        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface
        ) {
            when (spacecrafts) {
                is State.Loading -> {
                    ProgressView()
                }

                is State.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        items(spacecrafts.data) {
                            SpacecraftListItem(item = it)
                        }
                    }
                }

                is State.Error -> {
                    ErrorView(text = spacecrafts.message) {
                        viewModel.getSpacecrafts()
                    }
                }
            }
        }
    }


    @Preview
    @Composable
    private fun LoadingPreview() {
        ProgressView()
    }

    @Preview
    @Composable
    private fun ErrorPreview() {
        ErrorView(){ }
    }

    @Preview
    @Composable
    private fun SpacecraftScreenPreview() {
        AppTheme {
            //SpacecraftScreen(list = List(20) { Spacecraft(it, "Spacecraft $it") })
        }
    }

}