package com.absut.isro.archive.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    text: String? = null,
    buttonText: String? = null,
    onButtonClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text ?: "Something went wrong", style = MaterialTheme.typography.bodyMedium)
        Button(onClick = onButtonClick, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = buttonText ?: "Try again")
        }
    }
}