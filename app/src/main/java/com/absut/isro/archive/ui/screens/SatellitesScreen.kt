package com.absut.isro.archive.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.absut.isro.archive.R
import com.absut.isro.archive.data.model.CustomerSatellite
import com.absut.isro.archive.ui.ISROViewModel
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme

@Composable
fun SatelliteScreen(modifier: Modifier = Modifier, viewModel: ISROViewModel) {
    val satelliteState = viewModel.customerSatellites

    LaunchedEffect(Unit) {
        viewModel.getCustomerSatellites()
    }

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

@Composable
fun SatelliteListItem(modifier: Modifier = Modifier, item: CustomerSatellite) {
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
                    text = item.name.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )
                Text(
                    text = item.country.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )
                Text(
                    text = item.launchDate.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )
                Text(
                    text = item.mass.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )
                Text(
                    text = item.launcher.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )

            }
            Icon(
                painter = painterResource(id = R.drawable.ic_satellite_black_24dp),
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
        SatelliteListItem(
            item = CustomerSatellite(
                1,
                "Germany",
                "DLR-TUBSAT",
                "26-05-1989",
                "PSLV-C2",
                "45"
            )
        )
    }
}

@Preview
@Composable
private fun SpacecraftScreenPreview() {
    AppTheme {
        //SatelliteScreen(list = List(20) { CustomerSatellite(1, "Germany","DLR-TUBSAT","26-05-1989","PSLV-C2","45") })
    }
}
