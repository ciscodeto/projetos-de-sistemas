package com.ciscodeto.app4reinos.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StatBar(
    total: Int,
    current: Int,
    modifier: Modifier = Modifier,
    barHeight: Dp = 8.dp,
    backgroundColor: Color = Color(0x1affffff),
    foregroundColor: Color = Color(0xFFC01D20)
) {
    val healthPercent = (current.toFloat() / total).coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .padding(vertical = 4.dp)
            .height(barHeight)
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(healthPercent)
                .background(foregroundColor, shape = RoundedCornerShape(12.dp))
        )
    }
}