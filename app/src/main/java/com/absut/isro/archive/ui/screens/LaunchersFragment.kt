package com.absut.isro.archive.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.absut.isro.archive.ui.components.LauncherListItem
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.ui.ISROViewModel
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme

class LaunchersFragment : Fragment() {

    private val viewModel: ISROViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {

        viewModel.getLaunchers()

        AppTheme {
            LauncherScreen()
        }
    }

    @Composable
    fun LauncherScreen(modifier: Modifier = Modifier) {

        val launchers = viewModel.launchers

        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface
        ) {
            when (launchers) {
                is State.Loading -> {
                    ProgressView()
                }

                is State.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        items(launchers.data) {
                            LauncherListItem(item = it)
                        }
                    }
                }

                is State.Error -> {
                    ErrorView(text = launchers.message) {
                        viewModel.getSpacecrafts()
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    private fun LauncherScreenPreview() {
        AppTheme {
            //LauncherScreen(list = List(20) { Launcher(it, "Launcher $it") })
        }
    }


}