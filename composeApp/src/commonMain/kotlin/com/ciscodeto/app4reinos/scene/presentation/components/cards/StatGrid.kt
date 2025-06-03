package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun StatGrid(stats: Map<String, Int>) {
    val pairs = stats.toList().chunked(2)
    Column {
        for ((left, right) in pairs) {
            Row {
                Text(
                    text = "${left.first} ${left.second}",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${right.first} ${right.second}",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
