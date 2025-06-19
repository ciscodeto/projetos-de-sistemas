package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ciscodeto.app4reinos.scene.domain.CharacterUi

@Composable
fun CharacterSlot(
    actor: CharacterUi?,
    onSelectCharacter: () -> Unit,
    onRemoveCharacter: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Slot(
        entity = actor,
        card = { nonNullActor ->
            CharacterCard(
                character = nonNullActor,
                onClick = onRemoveCharacter,
            )
        },
        slotName = "Personagem",
        onSelect = onSelectCharacter,
        enabled = enabled,
        modifier = modifier,
        labelAnimation = "CharacterSlotAnimation"
    )
}