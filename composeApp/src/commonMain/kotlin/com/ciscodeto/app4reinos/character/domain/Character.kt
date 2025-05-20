package com.ciscodeto.app4reinos.character.domain

import com.ciscodeto.sinapsia.domain.character.Character

data class CharacterUi(
    val name: String = "",
    val level: Int = 1,

    val health: Int = 70,
    val stamina: Int = 50,

    val vitality: Int = -10,
    val energy: Int = -10,
    val strength: Int = -10,
    val endurance: Int = -10,
    val dexterity: Int = -10,
    val intelligence: Int = -10,
    val wisdom: Int = -10,
    val charisma: Int = -10
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
    fun from(character: Character) = CharacterUi(
        name = character.name,
        level = character.level,
        health = character.health,
        stamina = character.stamina,
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
