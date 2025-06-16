package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.actions.effects.Effect
import com.ciscodeto.sinapsia.domain.actions.effects.EffectType
import com.ciscodeto.sinapsia.domain.attributes.AttributeType
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.dice.DiceRoller
import com.ciscodeto.sinapsia.domain.shared.Entity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ConfigurableAction(
    id: ActionId,
    override val name: String,
    override val healthCost: Int,
    override val energyCost: Int,
    override val goldCost: Int,
    override val requiresTarget: Boolean,
    override val requiresReaction: Boolean,
    override val successAttributes: List<AttributeType>,
    override val effectAttributes: List<AttributeType>,
    val effectsOnSuccess: List<EffectType>,
    val counterActions: List<Action>
) : Entity<Uuid>(id), Action  {
    override fun execute(attributes: Attributes, target: Character?): ActionResult {
        val baseRoll = DiceRoller.roll("1d20")

        val attributeSum = successAttributes.sumOf { attributes.get(it) }
        val average = if (successAttributes.isNotEmpty()) attributeSum / successAttributes.size else 0

        val finalValue = baseRoll + average
        val success = finalValue > 12

        val effectValue = if (effectAttributes.isNotEmpty())
            effectAttributes.sumOf { attributes.get(it) } / effectAttributes.size else 0

        val effects = if (success) {
            effectsOnSuccess.map { effectType ->
                when (effectType) {
                    EffectType.DAMAGE -> Effect.Damage(effectValue)
                    EffectType.HEAL -> Effect.Heal(effectValue)
                    EffectType.ENERGY -> Effect.Energy(effectValue)
                    EffectType.DRAIN -> Effect.Drain(effectValue)
                    EffectType.STEAL -> Effect.Steal(effectValue)
                    EffectType.GOLD -> Effect.Gold(effectValue)
                    EffectType.MISS -> Effect.Miss
                }
            }
        } else {
            listOf(Effect.Miss)
        }

        val message = if (success) {
            "$name foi executada com sucesso! Valor final: $finalValue."
        } else {
            "$name falhou. Rolagem: $baseRoll, valor final: $finalValue."
        }

        return ActionResult(
            success = success,
            rollValue = baseRoll,
            finalValue = finalValue,
            counterActions = if (success) counterActions else emptyList(),
            effects = effects,
            message = message
        )
    }
}