package com.ciscodeto.domain.dice

import com.ciscodeto.domain.actions.Action
import com.ciscodeto.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.character.Character

class DiceClash(
    private val actor: Character,
    private val action: Action,
    private val target: Character,
) {
    private lateinit var actorResult: ActionResult
    private lateinit var counterResult: ActionResult

    fun executeInitial(): List<Action> {
        actorResult = actor.executeAction(action, target)
        return actorResult.counterActions
    }

    fun respondWith(counterAction: Action): Boolean {
        counterResult = target.executeAction(counterAction, actor)
        return true
    }

    fun resolve(): ClashOutcome {
        val effectApplied = when {
            !actorResult.success -> false
            counterResult == null -> true
            counterResult.finalValue < actorResult.finalValue -> true
            else -> false
        }

        val message = when {
            !actorResult.success -> "${actor.name} falhou em executar ${action.name}."
            counterResult == null -> "${actor.name} executou ${action.name} com sucesso contra ${target.name}."
            !counterResult!!.success -> "${target.name} falhou ao responder. ${actor.name} acertou ${action.name}!"
            !effectApplied -> "${target.name} defendeu com sucesso a ação ${action.name}!"
            else -> "${actor.name} superou a defesa de ${target.name} com ${action.name}!"
        }

        return ClashOutcome(
            actor = actor,
            target = target,
            action = action,
            actorResult = actorResult,
            counterResult = counterResult,
            counterAction = null,
            effectApplied = effectApplied,
            finalMessage = message
        )
    }
}