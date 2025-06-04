package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptySlot(
    text: String = "Slot",
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (enabled)  Color(0x802b1f1a) else Color(0x332b1f1a)
    val contentColor = if (enabled) Color(0xFF473229) else Color(0xb3473229)

    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(.75f)
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp),
            )
            .border(
                border = BorderStroke(1.dp, contentColor),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(enabled = enabled, onClick = onClick)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = contentColor
        )
    }
}