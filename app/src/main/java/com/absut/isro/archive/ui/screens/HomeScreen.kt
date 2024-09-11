package com.absut.isro.archive.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.absut.isro.archive.R
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_CENTRE
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_HOME
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_LAUNCHER
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_SATELLITE
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_SPACECRAFT
import com.absut.isro.archive.ui.components.HomeContentCard
import com.example.compose.AppTheme

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HomeContentCard(
            title = R.string.spacecraft_label,
            desc = R.string.spacecraft_desc,
            image = R.drawable.ic_rocket_black_24dp
        ) {
            navController.navigate(ROUTE_SPACECRAFT)
        }
        Spacer(modifier = Modifier.height(12.dp))

        HomeContentCard(
            title = R.string.launchers_label,
            desc = R.string.launchers_desc,
            image = R.drawable.ic_rocket_launch_black_24dp
        ) {
            navController.navigate(ROUTE_LAUNCHER)
        }
        Spacer(modifier = Modifier.height(12.dp))

        HomeContentCard(
            title = R.string.customer_satellite_label,
            desc = R.string.customer_satellite_desc,
            image = R.drawable.ic_satellite_black_24dp
        ) {
            navController.navigate(ROUTE_SATELLITE)
        }
        Spacer(modifier = Modifier.height(12.dp))

        HomeContentCard(
            title = R.string.centres_label,
            desc = R.string.centres_desc,
            image = R.drawable.ic_hub_black_24dp
        ) {
            navController.navigate(ROUTE_CENTRE)
        }
    }

}


@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreviewNight() {
    AppTheme {
        //HomeScreen()
    }
}