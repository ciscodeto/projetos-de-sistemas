package com.ciscodeto.domain.dice

import com.ciscodeto.domain.actions.Action
import com.ciscodeto.domain.actions.ActionResult
import com.ciscodeto.domain.character.Character

class DiceClash(
    val actor: Character,
    val action: Action,
    val target: Character,
) {
    lateinit var actorResult: ActionResult
    lateinit var counterResult: ActionResult

    fun executeInitial(): List<Action> {
        actorResult = actor.executeAction(action, target)
        return actorResult.counterActions
    }

    fun respondWith(counterAction: Action): Boolean {
        counterResult = target.executeAction(counterAction, actor)
        return true
    }

    fun resolve(): CombatResult {

    }
}