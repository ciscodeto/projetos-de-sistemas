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
    card: @Composable (innerEntity: T) -> Unit, // Lambda agora recebe a entidade não-nula
    slotName: String = "Slot",
    onSelect: () -> Unit,
    enabled: Boolean = true, // Controla a clicabilidade do EmptySlot
    modifier: Modifier = Modifier,
    labelAnimation: String = "SlotAnimation"
) {
    AnimatedContent(
        targetState = entity,
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
        label = labelAnimation // Usando o parâmetro para o label da animação
    ) { currentEntityState -> // currentEntityState é o T? do targetState
        if (currentEntityState != null) {
            card(currentEntityState) // Passa a entidade não-nula para a lambda card
        } else {
            EmptySlot(
                text = slotName,
                enabled = enabled,
                onClick = onSelect
            )
        }
    }
}