package com.absut.isro.archive.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.absut.isro.archive.R
import com.absut.isro.archive.data.model.Centre
import com.example.compose.AppTheme

@Composable
fun CenterListItem(modifier: Modifier = Modifier, item: Centre) {
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
                    style = MaterialTheme.typography.bodyMedium,
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
                Text(
                    text = item.place.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )
                Text(
                    text = item.state.toString(),
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_hub_black_24dp),
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
private fun CenterListItemPreview() {
    AppTheme {
        CenterListItem(
            item = Centre(
                1,
                "Semi-Conductor Laboratory (SCL)",
                "Chandigarh",
                "Punjab/Haryana"
            )
        )
    }
}