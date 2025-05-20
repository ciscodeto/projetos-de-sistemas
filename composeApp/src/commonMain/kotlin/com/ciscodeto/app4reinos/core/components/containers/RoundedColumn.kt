package com.ciscodeto.app4reinos.core.components.containers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF2B1F1A),
                shape = RoundedCornerShape(8.dp),
            )
            .border(
                border = BorderStroke(1.dp, Color(0xFF473229)),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}