package com.ciscodeto.app4reinos.character.domain

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class CharacterUi @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid? = null,
    val name: String = "",
    val level: Int = 1,

    val currentHealth: Int = 50,
    val currentEnergy: Int = 50,

    val vitality: Int = 0,
    val energy: Int = 0,
    val strength: Int = 0,
    val endurance: Int = 0,
    val dexterity: Int = 0,
    val intelligence: Int = 0,
    val wisdom: Int = 0,
    val charisma: Int = 0
) {
    fun availablePoints(): Int {
        return level * 2 + 30 - vitality - energy - strength - endurance - dexterity - intelligence - wisdom - charisma
    }

    fun attributes(): List<AttributeUi> = listOf(
        AttributeUi("FORÇA", strength),
        AttributeUi("RESISTÊNCIA", endurance),
        AttributeUi("AGILIDADE", dexterity),
        AttributeUi("INTELIGÊNCIA", intelligence),
        AttributeUi("SABEDORIA", wisdom),
        AttributeUi("CARISMA", charisma)
    )
    @OptIn(ExperimentalUuidApi::class)
    fun from(character: CharacterDto) = CharacterUi(
        id = character.id,
        name = character.name,
        level = character.level,
        currentHealth = character.currentHealth,
        currentEnergy = character.currentEnergy,
        vitality = character.attributes.vitality,
        energy = character.attributes.energy,
        strength = character.attributes.strength,
        endurance = character.attributes.endurance,
        dexterity = character.attributes.dexterity,
        intelligence = character.attributes.intelligence,
        wisdom = character.attributes.wisdom,
        charisma = character.attributes.charisma
    )

    fun getAttribute(name: String): Int {
        return when (name.lowercase()) {
            "vitalidade" -> vitality
            "energia" -> energy
            "força" -> strength
            "resistência" -> endurance
            "agilidade" -> dexterity
            "inteligência" -> intelligence
            "sabedoria" -> wisdom
            "carisma" -> charisma
            else -> 0
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun setAttribute(name: String, value: Int): CharacterUi {
        return when (name.lowercase()) {
            "vitalidade" -> copy(vitality = value)
            "energia" -> copy(energy = value)
            "força" -> copy(strength = value)
            "resistência" -> copy(endurance = value)
            "agilidade" -> copy(dexterity = value)
            "inteligência" -> copy(intelligence = value)
            "sabedoria" -> copy(wisdom = value)
            "carisma" -> copy(charisma = value)
            else -> this
        }
    }

}
