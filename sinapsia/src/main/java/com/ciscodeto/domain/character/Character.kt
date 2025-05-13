package com.ciscodeto.domain.character

import com.ciscodeto.domain.actions.Action
import com.ciscodeto.domain.actions.ActionResult
import com.ciscodeto.domain.shared.Entity
import com.ciscodeto.domain.shared.Notification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Character(
    id: CharacterId,
    val name: String,
    val age: Int,
    var level: Int,
    var experience: Int,
    var gold: Int,
    var health: Int,
    var energy: Int,
    var attributes: List<Attribute>,
    var attributeModifier: List<Attribute>,
    var inventory: List<Item>,
    var relationships: Map<Character, String>,
) : Entity<Uuid>(id) {
    private var attributePoints: Int
    private var totalSpent = attributes.sumOf { it.value } + attributeModifier.sumOf { it.value }

    init {
        attributePoints = calculateAttributePoints()
        val notification = validate()
        require(notification.hasNoErrors()) { notification.message() }
    }

    fun levelUp() {
        level++
        attributePoints = calculateAttributePoints()
    }

    fun applyAttributeUpgrades(upgrades: List<Attribute>) {
        val totalCost = upgrades.sumOf { it.value }

        require(totalCost <= attributePoints) {
            "Not enough attribute points. Required: $totalCost, Available: $attributePoints"
        }
    }

    private fun validate() = Notification().also {
        if (name.isBlank())
            it.addError("Character name must not be blank!")
        if (age < 0)
            it.addError("Character age must not be negative!")
        if (totalSpent <= calculateAttributePoints())
            it.addError("Character attribute points are exceeding the limit!")
    }

    private fun calculateAttributePoints() = (level * 2) + 30 - attributes.sumOf { it.value }
}
