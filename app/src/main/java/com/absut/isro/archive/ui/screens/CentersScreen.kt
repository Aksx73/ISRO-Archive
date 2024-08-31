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
import com.absut.isro.archive.ui.ISROViewModel
import com.absut.isro.archive.ui.components.CenterListItem
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme

@Composable
fun CentersScreen(modifier: Modifier = Modifier, viewModel: ISROViewModel) {
    viewModel.getCentres()
    val centersState = viewModel.centres

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        when (centersState) {
            is State.Loading -> {
                ProgressView()
            }

            is State.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    items(centersState.data) {
                        CenterListItem(item = it)
                    }
                }
            }

            is State.Error -> {
                ErrorView(text = centersState.message) {
                    viewModel.getCentres()
                }

            }
        }

    }
}

@Preview
@Composable
private fun CentersScreenPreview() {
    AppTheme {
        /* CentersScreen(list = List(20) { Centre(
                 it,
                 "Center name $it",
                 "Place $it",
                 "State name $it"
             )
         })*/
    }
}