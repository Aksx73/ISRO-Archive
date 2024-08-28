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
import androidx.fragment.compose.content
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.ui.components.SpacecraftListItem
import com.absut.isro.archive.ui.ISROViewModel
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme


class SpacecraftFragment : Fragment() {

    private val viewModel: ISROViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {

        viewModel.getSpacecrafts()

        AppTheme {
            SpacecraftScreen()
        }
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
    private fun SpacecraftScreenPreview() {
        AppTheme {
            //SpacecraftScreen(list = List(20) { Spacecraft(it, "Spacecraft $it") })
        }
    }

}