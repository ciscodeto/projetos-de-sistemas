package com.ciscodeto.sinapsia.domain.dice

import com.ciscodeto.sinapsia.application.character.update.UpdateCharacter
import com.ciscodeto.sinapsia.domain.actions.Action
import com.ciscodeto.sinapsia.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi

/**
 * Gerencia o confronto entre uma ação inicial do ator e uma possível reação do alvo.
 * Determina o resultado final do confronto, incluindo os efeitos a serem aplicados.
 */
class DiceClash(
    private val actor: Character,
    private val initialAction: Action,
    private val target: Character?,
    private val updateCharacter: UpdateCharacter
) {
    private lateinit var actorActionResult: ActionResult
    private var chosenCounterAction: Action? = null
    private var counterActionResult: ActionResult? = null

    /**
     * O ator executa a ação inicial.
     * @return O ActionResult da ação do ator.
     */
    fun executeInitialAction(): ActionResult {
        actorActionResult = actor.executeAction(initialAction, target)
        return actorActionResult
    }

    /**
     * O alvo executa uma ação de reação em resposta à ação inicial do ator.
     * @param reaction A ação de reação escolhida pelo alvo.
     * @return O ActionResult da reação do alvo, ou null se a reação não for válida/possível.
     */
    fun executeCounterAction(reaction: Action): ActionResult? {
        if (target == null) {
            return null
        }
        chosenCounterAction = reaction
        counterActionResult = target.executeAction(reaction, actor)
        return counterActionResult
    }

    /**
     * Resolve o confronto com base nos resultados da ação inicial e da possível reação.
     * @return Um ClashOutcome detalhando o resultado do confronto.
     */
    fun resolveClash(): ClashOutcome {
        val effectsToApplyList = mutableListOf<EffectsToApply>()
        val finalMessage: String

        if (!actorActionResult.success) {
            finalMessage = "${actor.name} falhou em executar ${initialAction.name}."
            if (actorActionResult.effects.isNotEmpty()) {
                effectsToApplyList.add(EffectsToApply(actorActionResult.effects, actor))
            }
        }
        else {
            if (chosenCounterAction == null || counterActionResult == null) {
                finalMessage = "${actor.name} usou ${initialAction.name}" +
                        (if (target != null) " em ${target.name}" else " em si mesmo") +
                        " com sucesso (sem reação efetiva)."
                if (target != null && actorActionResult.effects.isNotEmpty()) {
                    effectsToApplyList.add(EffectsToApply(actorActionResult.effects, target))
                } else if (target == null && actorActionResult.effects.isNotEmpty()){
                    effectsToApplyList.add(EffectsToApply(actorActionResult.effects, actor))
                }
            }
            else if (!counterActionResult!!.success) {
                finalMessage = "${target?.name ?: "Alvo"} tentou reagir com ${chosenCounterAction?.name ?: "Reação"}, mas falhou. " +
                        "${actor.name} acertou com ${initialAction.name}!"
                if (target != null && actorActionResult.effects.isNotEmpty()) {
                    effectsToApplyList.add(EffectsToApply(actorActionResult.effects, target))
                } else if (target == null && actorActionResult.effects.isNotEmpty()){
                    effectsToApplyList.add(EffectsToApply(actorActionResult.effects, actor))
                }
                if (counterActionResult!!.effects.isNotEmpty()) {
                    effectsToApplyList.add(EffectsToApply(counterActionResult!!.effects, actor))
                }
            }
            else {
                val currentTarget = target!!
                val currentCounterAction = chosenCounterAction!!
                val currentCounterResult = counterActionResult!!

                if (actorActionResult.finalValue > currentCounterResult.finalValue) {
                    finalMessage = "${actor.name} superou a reação de ${currentTarget.name} (${currentCounterAction.name}) com ${initialAction.name}!"
                    if (actorActionResult.effects.isNotEmpty()) {
                        effectsToApplyList.add(EffectsToApply(actorActionResult.effects, currentTarget))
                    }

                     if (currentCounterResult.effects.isNotEmpty()) {
                        effectsToApplyList.add(EffectsToApply(currentCounterResult.effects, actor))
                     }
                } else if (actorActionResult.finalValue < currentCounterResult.finalValue) {
                    finalMessage = "${currentTarget.name} com ${currentCounterAction.name} defendeu/superou a ação de ${actor.name} (${initialAction.name})!"
                    if (currentCounterResult.effects.isNotEmpty()) {
                        effectsToApplyList.add(EffectsToApply(currentCounterResult.effects, actor))
                    }
                } else {
                    finalMessage = "A ação de ${actor.name} (${initialAction.name}) e a reação de ${currentTarget.name} (${currentCounterAction.name}) se anularam (empate)!"
                }
            }
        }

        return ClashOutcome(
            actor = actor,
            target = target,
            action = initialAction,
            actorResult = actorActionResult,
            counterAction = chosenCounterAction,
            counterResult = counterActionResult,
            effectsToApply = effectsToApplyList,
            finalMessage = finalMessage
        )
    }

    @OptIn(ExperimentalUuidApi::class)
    suspend fun applyResolvedEffects(outcome: ClashOutcome): List<String> {
        val logMessages = mutableListOf<String>()
        logMessages.add(outcome.finalMessage)

        outcome.effectsToApply.forEach { effectsGroup ->
            val characterToAffect = effectsGroup.target
            effectsGroup.effects.forEach { effect ->
                val effectMessage = effect.apply(characterToAffect)

                if (effectMessage.isNotBlank() && effectMessage != "Miss") {
                    logMessages.add("${characterToAffect.name}: $effectMessage")
                }
            }
            updateCharacter.update(UpdateCharacter.RequestModel(
                id = characterToAffect.id.value(),
                name = characterToAffect.name,
                currentHealth = characterToAffect.currentHealth,
                currentEnergy = characterToAffect.currentEnergy,
                gold = characterToAffect.gold,
                attributes = characterToAffect.attributes,
                description = "",
                age = 0,
                level = characterToAffect.level,
                experience = characterToAffect.experience,
            ))
        }
        return logMessages
    }
}