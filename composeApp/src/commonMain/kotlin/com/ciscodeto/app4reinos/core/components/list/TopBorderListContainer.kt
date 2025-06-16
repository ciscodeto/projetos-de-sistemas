package com.ciscodeto.app4reinos.core.components.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopBorderListContainer(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
    borderColorStart: Color = Color(0xFFD0CBC3),
    borderColorEnd: Color = Color(0xFFB3A083),
    containerBackgroundColor: Color = Color(0xFF120A06).copy(alpha = 0.7f),
    animateOnEntry: Boolean = true,
    animationDurationMillis: Int = 500,
    animationDelayMillis: Int = 100,
    content: @Composable BoxScope.() -> Unit
) {
    val borderBrush = Brush.verticalGradient(colors = listOf(borderColorStart, borderColorEnd))
    val contentAreaShape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius)

    var isVisible by remember { mutableStateOf(!animateOnEntry) }

    if (animateOnEntry) {
        LaunchedEffect(key1 = Unit) {
            isVisible = true
        }
    }

    AnimatedVisibility(
        visible = isVisible ,
        enter = slideInVertically (
            initialOffsetY = { it / 3 },
            animationSpec = tween(durationMillis = animationDurationMillis, delayMillis = animationDelayMillis)
        ) + fadeIn(
            animationSpec = tween(durationMillis = animationDurationMillis * 3 / 4, delayMillis = animationDelayMillis)
        ),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.drawBehind {
                val strokeWidthPx = borderWidth.toPx()
                val cornerRadiusPx = cornerRadius.toPx()
                val inset = strokeWidthPx / 2f

                val path = Path().apply {
                    moveTo(inset, size.height - inset)
                    lineTo(inset, cornerRadiusPx)
                    arcTo(
                        rect = Rect(
                            inset,
                            inset,
                            inset + 2 * (cornerRadiusPx - inset),
                            inset + 2 * (cornerRadiusPx - inset)
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(size.width - cornerRadiusPx, inset)
                    arcTo(
                        rect = Rect(
                            size.width - (2 * (cornerRadiusPx - inset)) - inset,
                            inset,
                            size.width - inset,
                            inset + 2 * (cornerRadiusPx - inset)
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(size.width - inset, size.height - inset)
                }
                drawPath(
                    path,
                    brush = borderBrush,
                    style = Stroke(width = strokeWidthPx, cap = StrokeCap.Butt)
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(
                        start = borderWidth,
                        top = borderWidth,
                        end = borderWidth,
                        bottom = 0.dp

                    )
                    .background(containerBackgroundColor, contentAreaShape)
                    .clip(contentAreaShape),
                content = content
            )
        }
    }
}