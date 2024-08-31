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
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.ui.components.SatelliteListItem
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme

@Composable
fun SatelliteScreen(modifier: Modifier = Modifier,viewModel: ISROViewModel) {
    viewModel.getCustomerSatellites()
    val satelliteState = viewModel.customerSatellites

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        when (satelliteState) {
            is State.Loading -> {
                ProgressView()
            }

            is State.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    items(satelliteState.data) {
                        SatelliteListItem(item = it)
                    }
                }
            }

            is State.Error -> {
                ErrorView(text = satelliteState.message) {
                    viewModel.getCustomerSatellites()
                }
            }
        }
    }
}

@Preview
@Composable
private fun SpacecraftScreenPreview() {
    AppTheme {
        //SatelliteScreen(list = List(20) { CustomerSatellite(1, "Germany","DLR-TUBSAT","26-05-1989","PSLV-C2","45") })
    }
}
