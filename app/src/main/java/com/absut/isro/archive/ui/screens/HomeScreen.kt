package com.absut.isro.archive.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.absut.isro.archive.R
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_CENTRE
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_LAUNCHER
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_SATELLITE
import com.absut.isro.archive.ui.MainActivity.Companion.ROUTE_SPACECRAFT
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

@Composable
fun HomeContentCard(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes desc: Int,
    @DrawableRes image: Int,
    clickListener: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = Shapes().small,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        color = MaterialTheme.colorScheme.surface,
        onClick = clickListener
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium)),
                )
                Text(
                    text = stringResource(desc),
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_regular))
                )
            }
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreviewNight() {
    AppTheme {
        HomeContentCard(
            title = R.string.centres_label,
            desc = R.string.centres_desc,
            image = R.drawable.ic_hub_black_24dp
        )
    }
}