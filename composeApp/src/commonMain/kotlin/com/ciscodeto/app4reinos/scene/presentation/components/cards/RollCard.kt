package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DiceRollCard(
    onRollClicked: () -> Unit,
    onEditClicked: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val cardGradientStart = Color(0xFF1A1F2B)
    val cardGradientEnd = Color(0xFF0F2A34)
    val cardBorderColor = Color(0xFF00D5FF)
    val hexagonGradientStart = Color(0xFF1A8A9E)
    val hexagonGradientEnd = Color(0xFF0F5A68)
    val textColor = Color(0xFF00D5FF)
    val iconButtonColor = Color(0xFF00D5FF)

    val contentAlpha = if (enabled) 1.0f else 0.5f

    val hexagonShape = remember { HexagonShape() }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .graphicsLayer(alpha = contentAlpha)
            .aspectRatio(3f / 4f)
            .clip(RoundedCornerShape(4.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(cardGradientStart, cardGradientEnd)
                )
            )
            .border(1.dp, cardBorderColor, RoundedCornerShape(4.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(0.7f) // Ocupa 60% do espaço do card
                .clip(hexagonShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(hexagonGradientStart, hexagonGradientEnd)
                    )
                )
                .border(width = 1.dp, color = cardBorderColor, shape = hexagonShape)
                .clickable(enabled = enabled, onClick = onRollClicked)
                .drawWithContent {
                    drawContent()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ROLAR!",
                color = textColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin,
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(24.dp)
                .offset(x = 8.dp, y = 8.dp)
                .clip(CircleShape)
                .background(iconButtonColor)
                .clickable(enabled = enabled, onClick = onEditClicked)
                .drawWithContent {
                    drawContent()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Editar Rolagem",
                tint = textColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

class HexagonShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                val radius = size.minDimension / 2f
                val angle = 2.0 * PI / 6
                val startAngle = PI / 2
                val center = Offset(size.width / 2f, size.height / 2f)

                moveTo(
                    center.x + radius * cos(startAngle).toFloat(),
                    center.y + radius * sin(startAngle).toFloat()
                )
                for (i in 1 until 6) {
                    lineTo(
                        center.x + radius * cos(startAngle + angle * i).toFloat(),
                        center.y + radius * sin(startAngle + angle * i).toFloat()
                    )
                }
                close()
            },
        )
    }
}