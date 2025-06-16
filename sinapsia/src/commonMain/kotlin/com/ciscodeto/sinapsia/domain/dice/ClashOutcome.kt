package com.ciscodeto.sinapsia.domain.dice

import com.ciscodeto.sinapsia.domain.actions.Action
import com.ciscodeto.sinapsia.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.actions.effects.Effect
import com.ciscodeto.sinapsia.domain.character.Character

data class ClashOutcome(
    val actor: Character,
    val target: Character?,
    val action: Action,
    val actorResult: ActionResult,
    val counterAction: Action?,
    val counterResult: ActionResult?,
    val effectsToApply: List<EffectsToApply>,
    val finalMessage: String
)

data class EffectsToApply(
    val effects: List<Effect>,
    val target: Character
)
