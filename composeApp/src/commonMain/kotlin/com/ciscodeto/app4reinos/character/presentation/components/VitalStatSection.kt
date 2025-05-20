package com.ciscodeto.app4reinos.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VitalStatSection(
    title: String,
    value: Int,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, color = Color(0xFFD6BFA1), fontSize = 16.sp)
            AttributeCounter(value = value)
        }
        LinearProgressIndicator(
            gapSize = (value / 100f).dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = Color(0xFFE7DFD6),
            trackColor = Color.DarkGray
        )
    }
}

@Composable
fun LifeBar(
    currentHealth: Int,
    maxHealth: Int,
    modifier: Modifier = Modifier,
    barHeight: Dp = 20.dp,
    backgroundColor: Color = Color(0x1affffff),
    foregroundColor: Color = Color(0xFFC01D20)
) {
    val healthPercent = (currentHealth.toFloat() / maxHealth).coerceIn(0f, 1f)

    Box(
        modifier = modifier
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