package com.ciscodeto.sinapsia.domain.character

import com.ciscodeto.sinapsia.domain.actions.Action
import com.ciscodeto.sinapsia.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.actions.effects.Effect
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.item.Item
import com.ciscodeto.sinapsia.domain.shared.Entity
import com.ciscodeto.sinapsia.domain.shared.Notification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Character(
    id: CharacterId,
    val name: String,
    val age: Int,
    val description: String,
    var level: Int,
    var experience: Int,
    currentHealth: Int,
    currentEnergy: Int,
    gold: Int,
    var attributes: Attributes,
    var attributeModifier: Attributes? = null,
    var inventory: List<Item> = emptyList(),
) : Entity<Uuid>(id) {
    private val maxHealth: Int
        get() = calculateMaxHealth(attributes.vitality)

    private val maxEnergy: Int
        get() = calculateMaxEnergy(attributes.energy)

    var currentHealth: Int = currentHealth.coerceIn(0, maxHealth)
        set(value) {
            field = value.coerceIn(0, maxHealth)
        }

    var currentEnergy: Int = currentEnergy.coerceIn(0, maxEnergy)
        set(value) {
            field = value.coerceIn(0, maxEnergy)
        }

    var gold: Int = gold.coerceAtLeast(0)
        set(value) {
            field = value.coerceAtLeast(0)
        }

    private var attributePoints: Int

    companion object {
        private const val BASE_ATTRIBUTE_POINTS = 30

        private const val HEALTH_PER_POINTS = 10

        private const val ENERGY_PER_POINTS = 10

        fun remainingAttributePoints(attributes: Attributes, level: Int): Int {
            return maxAvailablePoints(level) - attributes.pointsSpentSince()
        }

        private fun maxAvailablePoints(level: Int = 1): Int {
            return BASE_ATTRIBUTE_POINTS + (level * 2)
        }

        fun calculateMaxEnergy(energy: Int): Int {
            return energy * ENERGY_PER_POINTS
        }

        fun calculateMaxHealth(vitality: Int): Int {
            return vitality * HEALTH_PER_POINTS
        }
    }

    init {
        attributePoints = remainingAttributePoints(attributes, level)
        val notification = validate()
        require(notification.hasNoErrors()) { notification.message() }
    }

    fun executeAction(action: Action, target: Character?): ActionResult {
        if (this.currentHealth <= action.healthCost) {
            return ActionResult(
                success = false,
                finalValue = 0,
                message = "${this.name} não tem vida suficiente para usar ${action.name}.",
                effects = listOf(Effect.Miss),
            )
        }
        if (this.currentEnergy < action.energyCost) {
            return ActionResult(
                success = false,
                finalValue = 0,
                message = "${this.name} não tem energia suficiente para usar ${action.name}.",
                effects = listOf(Effect.Miss)
            )
        }
        if (this.gold < action.goldCost) {
            return ActionResult(
                success = false,
                finalValue = 0,
                message = "${this.name} não tem ouro suficiente para usar ${action.name}.",
                effects = listOf(Effect.Miss)
            )
        }

        this.currentHealth -= action.healthCost
        this.currentEnergy -= action.energyCost
        this.gold -= action.goldCost

        val actionResult = action.execute(this.attributes, target)

        if (!actionResult.success) {
            val refundPercentage = 0.5

            val healthRefund = (action.healthCost * refundPercentage).toInt()
            val energyRefund = (action.energyCost * refundPercentage).toInt()
            val goldRefund = (action.goldCost * refundPercentage).toInt()

            this.currentHealth += healthRefund

            if (this.currentHealth > this.maxHealth) {
                this.currentHealth = this.maxHealth
            }

            this.currentEnergy += energyRefund

            if (this.currentEnergy > this.maxEnergy) {
                this.currentEnergy = this.maxEnergy
            }

            this.gold += goldRefund
        }
        return actionResult
    }

    fun levelUp() {
        levelUp(1)
    }

    private fun levelUp(level: Int) {
        this.level += level
        attributePoints = remainingAttributePoints(attributes, level)
    }

    private fun validate() = Notification().also {
        val maxPoints = maxAvailablePoints(level)

        if (attributes.total() > maxPoints)
            it.addError("Total attribute points (${attributes.total()}) exceed the allowed limit of $maxPoints")
        if (name.isBlank())
            it.addError("Character name must not be blank!")
        if (age < 0)
            it.addError("Character age must not be negative!")
    }
}
