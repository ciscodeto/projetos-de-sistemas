package com.ciscodeto.sinapsia.domain.character

import com.ciscodeto.sinapsia.domain.actions.Action
import com.ciscodeto.sinapsia.domain.actions.ActionResult
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
    var gold: Int,
    var currentHealth: Int,
    var currentEnergy: Int,
    var attributes: Attributes,
    var attributeModifier: Attributes? = null,
    var inventory: List<Item> = emptyList(),
) : Entity<Uuid>(id) {
    private var attributePoints: Int
    companion object {
        private const val BASE_ATTRIBUTE_POINTS = 30

        private const val HEALTH_PER_POINTS = 10
        private const val BASE_HEALTH = 50

        private const val ENERGY_PER_POINTS = 10
        private const val BASE_ENERGY = 50

        fun remainingAttributePoints(attributes: Attributes, level: Int): Int {
            return maxAvailablePoints(level) - attributes.total()
        }

        private fun maxAvailablePoints(level: Int = 1): Int {
            return BASE_ATTRIBUTE_POINTS + (level * 2)
        }

        fun calculateMaxEnergy(energy: Int): Int {
            return energy * ENERGY_PER_POINTS + BASE_ENERGY
        }

        fun calculateMaxHealth(vitality: Int): Int {
            return vitality * HEALTH_PER_POINTS + BASE_HEALTH
        }
    }

    init {
        attributePoints = remainingAttributePoints(attributes, level)
        val notification = validate()
        require(notification.hasNoErrors()) { notification.message() }
    }

    fun executeAction(action: Action, target: Character) : ActionResult {
        return action.execute(attributes, target)
    }

    fun levelUp() {
        levelUp(1)
    }

    fun levelUp(level: Int) {
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
