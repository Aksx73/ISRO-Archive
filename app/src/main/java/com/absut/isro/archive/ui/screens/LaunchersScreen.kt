package com.absut.isro.archive.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.absut.isro.archive.ui.ISROViewModel
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.LauncherListItem
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme

@Composable
fun LauncherScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: ISROViewModel) {
    //viewModel.getLaunchers()
    val launchersState = viewModel.launchers

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        when (launchersState) {
            is State.Loading -> {
                ProgressView()
            }

            is State.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    items(launchersState.data) {
                        LauncherListItem(item = it)
                    }
                }
            }

            is State.Error -> {
                ErrorView(text = launchersState.message) {
                    viewModel.getLaunchers()
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
