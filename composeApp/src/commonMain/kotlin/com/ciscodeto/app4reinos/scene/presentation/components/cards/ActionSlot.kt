package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ciscodeto.app4reinos.scene.domain.ActionUi

@Composable
fun ActionSlot(
    action: ActionUi?,
    onSelect: () -> Unit,
    onRemove: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Slot(
        entity = action,
        card = { nonNullAction ->
            ActionCard(
                action = nonNullAction,
                onClick = onRemove
            )
        },
        slotName = "Ação",
        onSelect = onSelect,
        enabled = enabled,
        modifier = modifier,
        labelAnimation = "ActionSlotAnimation"
    )
}
