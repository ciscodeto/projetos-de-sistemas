package com.ciscodeto.sinapsia.domain.character

import com.ciscodeto.domain.actions.Action
import com.ciscodeto.domain.actions.ActionResult
import com.ciscodeto.domain.character.CharacterId
import com.ciscodeto.domain.character.Item
import com.ciscodeto.domain.shared.Entity
import com.ciscodeto.domain.shared.Notification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Character(
    id: CharacterId,
    val name: String,
    val description: String,
    val age: Int,
    var level: Int,
    var experience: Int,
    var gold: Int,
    var health: Int,
    var stamina: Int,
    var attributes: Attributes,
    var attributeModifier: Attributes?,
    var inventory: List<Item> = emptyList(),
) : Entity<Uuid>(id) {
    private var attributePoints: Int

    init {
        attributePoints = calculateAttributePoints()
        val notification = validate()
        require(notification.hasNoErrors()) { notification.message() }
    }

    fun executeAction(action: Action, target: Character) : ActionResult {
        return action.execute(attributes, target)
    }

    fun levelUp() {
        level++
        attributePoints = calculateAttributePoints()
    }

    private fun calculateAttributePoints() = (level * 2) + 30 - attributes.total()

    private fun validate() = Notification().also {
        if (name.isBlank())
            it.addError("Character name must not be blank!")
        if (age < 0)
            it.addError("Character age must not be negative!")
        if (attributes.total() <= calculateAttributePoints())
            it.addError("Character attribute points are exceeding the limit!")
    }
}
