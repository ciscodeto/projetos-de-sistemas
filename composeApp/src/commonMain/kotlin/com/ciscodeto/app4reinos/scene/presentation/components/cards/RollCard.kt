package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
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
    enabled: Boolean = true
) {
    val cardBackgroundColor = Color(0xFF1A1F26)
    val cardBorderColor = Color(0xFF2E3D4F)
    val hexagonGradientStart = Color(0xFF1A8A9E)
    val hexagonGradientEnd = Color(0xFF0F5A68)
    val textColor = Color(0xFFB3E5FC)
    val iconButtonColor = Color(0xFF246C7C)

    val contentAlpha = if (enabled) 1.0f else 0.5f

    Box(
        modifier = modifier
            .aspectRatio(3f / 4f) // Proporção de um card de baralho
            .clip(RoundedCornerShape(4.dp))
            .background(cardBackgroundColor)
            .border(2.dp, cardBorderColor, RoundedCornerShape(4.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Botão Hexagonal Central
        Box(
            modifier = Modifier
                .fillMaxSize(0.6f) // Ocupa 60% do espaço do card
                .clip(HexagonShape())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(hexagonGradientStart, hexagonGradientEnd)
                    )
                )
                .graphicsLayer(alpha = contentAlpha)
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
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        // Botão de Edição (Lápis)
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(24.dp) // Tamanho do botão
                .offset(x = 8.dp, y = 8.dp) // Desloca um pouco para fora do canto
                .clip(CircleShape)
                .background(iconButtonColor)
                .graphicsLayer(alpha = contentAlpha)
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
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                val radius = size.minDimension / 2f
                val angle = 2.0 * PI / 6
                val center = Offset(size.width / 2f, size.height / 2f)

                moveTo(
                    center.x + radius * cos(0.0).toFloat(),
                    center.y + radius * sin(0.0).toFloat()
                )
                for (i in 1 until 6) {
                    lineTo(
                        center.x + radius * cos(angle * i).toFloat(),
                        center.y + radius * sin(angle * i).toFloat()
                    )
                }
                close()
            }
        )
    }
}