package com.ciscodeto.domain.entities.character

import com.ciscodeto.domain.entities.Attribute
import com.ciscodeto.domain.entities.Item
import com.ciscodeto.domain.shared.Entity
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
    var attributePoints: Int,
    var attributeModifier: List<Attribute>,
    var inventory: List<Item>,
    var relationships: Map<Character, String>,
) : Entity<Uuid>(id) {
    fun levelUp() {
        level++
        attributePoints = calculateAttributePoints()
    }

    private fun calculateAttributePoints() = (level * 2) + 30 - attributes.sumOf { it.value }
}
