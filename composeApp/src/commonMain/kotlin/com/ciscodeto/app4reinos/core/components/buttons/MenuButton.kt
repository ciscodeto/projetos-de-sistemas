package com.ciscodeto.app4reinos.core.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface {
        Button(
            modifier = modifier,
            onClick = {
                onClick()
            },
        ) {
            Text(text)
        }
    }
}