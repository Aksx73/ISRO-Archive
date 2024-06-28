package com.absut.isro.archive.ui.spacecraft

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.R
import com.absut.isro.archive.data.model.Spacecraft
import com.absut.isro.archive.databinding.FragmentSpacecraftBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.adapter.SpacecraftAdapter
import com.absut.isro.archive.ui.common.ErrorView
import com.absut.isro.archive.ui.common.ProgressView
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.Resource
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


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
                    ErrorView() {
                        viewModel.getSpacecrafts()
                    }
                }
            }
        }
    }

    @Composable
    fun SpacecraftListItem(modifier: Modifier = Modifier, item: Spacecraft) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = Shapes().small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            //color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.id.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_regular))
                    )
                    Text(
                        text = item.name.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_rocket_black_24dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                )
            }
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
    @Composable
    private fun SpacecraftListItemPreview() {
        AppTheme {
            SpacecraftListItem(item = Spacecraft(1, "Aryabhata"))
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