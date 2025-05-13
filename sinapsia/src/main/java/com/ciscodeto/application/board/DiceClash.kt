package com.ciscodeto.application.board

import com.ciscodeto.domain.actions.Action
import com.ciscodeto.domain.character.Character
import com.ciscodeto.domain.shared.Entity

class DiceClash(
    val actor: Character,
    val action: Action,
    val target: Character,
) {
}