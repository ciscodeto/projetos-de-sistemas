package com.ciscodeto.domain.actions

import com.ciscodeto.application.board.DiceRoller
import com.ciscodeto.domain.character.Attribute
import com.ciscodeto.domain.character.Character
import com.ciscodeto.domain.shared.Entity

class ActionContext(
    val actor: Character,
    val targets: List<Character>,
    val diceRoller: DiceRoller
)