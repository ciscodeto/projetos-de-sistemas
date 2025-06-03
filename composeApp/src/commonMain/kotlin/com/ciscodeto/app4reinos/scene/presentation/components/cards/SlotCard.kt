package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn
import kotlinx.coroutines.delay

@Composable
fun SlotCard(
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
        Text(text = text, color = contentColor)
    }
}