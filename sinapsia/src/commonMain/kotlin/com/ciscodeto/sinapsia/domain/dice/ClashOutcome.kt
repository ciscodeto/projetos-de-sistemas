package com.ciscodeto.domain.dice

import com.ciscodeto.domain.actions.Action
import com.ciscodeto.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.character.Character

data class ClashOutcome(
    val actor: Character,
    val target: Character,
    val action: Action,
    val actorResult: ActionResult,
    val counterAction: Action?,
    val counterResult: ActionResult?,
    val effectApplied: Boolean,
    val finalMessage: String,
)
