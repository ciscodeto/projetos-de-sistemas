package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T : Any> Slot(
    entity: T?,
    card: @Composable (innerEntity: T) -> Unit,
    slotName: String = "Slot",
    onSelect: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    labelAnimation: String = "SlotAnimation"
) {
    AnimatedContent(
        targetState = entity != null,
        transitionSpec = {
            (
                slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(durationMillis = 300)
                )
                        + fadeIn(animationSpec = tween(300))
                        + scaleIn(initialScale = 0.9f, animationSpec = tween(300))
                ).togetherWith(
                fadeOut(animationSpec = tween(200))
            )
        },
        modifier = modifier,
        label = labelAnimation
    ) { currentEntityState ->
        if (entity != null && currentEntityState) {
            card(entity)
        } else {
            EmptySlot(
                text = slotName,
                enabled = enabled,
                onClick = onSelect
            )
        }
    }
}