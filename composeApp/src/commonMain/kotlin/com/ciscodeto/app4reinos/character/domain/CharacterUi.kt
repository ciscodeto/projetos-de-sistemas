package com.ciscodeto.app4reinos.character.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Shield
import com.ciscodeto.app4reinos.character.domain.AttributeType.*
import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class CharacterUi @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid? = null,
    val name: String = "",
    val level: Int = 1,

    val currentHealth: Int = 50,
    val currentEnergy: Int = 50,

    val vitality: Int = 5,
    val energy: Int = 5,
    val strength: Int = -10,
    val endurance: Int = -10,
    val dexterity: Int = -10,
    val intelligence: Int = -10,
    val wisdom: Int = -10,
    val charisma: Int = -10
) {

    fun allAttributesUi(): List<AttributeUi> = vitalAttributesUi() + attributesUi()

    private fun vitalAttributesUi(): List<AttributeUi> = listOf(
        AttributeUi("VITALIDADE", VITALITY, vitality, Icons.Filled.Favorite),
        AttributeUi("ENERGIA", ENERGY, energy, Icons.Filled.Bolt)
    )

    fun attributesUi(): List<AttributeUi> = listOf(
        AttributeUi("FORÇA", STRENGTH, strength, Icons.Filled.FitnessCenter),
        AttributeUi("RESISTÊNCIA", ENDURANCE, endurance, Icons.Filled.Shield),
        AttributeUi("AGILIDADE", DEXTERITY, dexterity, Icons.AutoMirrored.Filled.DirectionsRun),
        AttributeUi("INTELIGÊNCIA", INTELLIGENCE, intelligence, Icons.Filled.Lightbulb),
        AttributeUi("SABEDORIA", WISDOM, wisdom, Icons.Filled.Book),
        AttributeUi("CARISMA", CHARISMA, charisma, Icons.Filled.Flare)
    )

    fun attributes(): Attributes = Attributes(
        vitality = vitality,
        energy = energy,
        strength = strength,
        endurance = endurance,
        dexterity = dexterity,
        intelligence = intelligence,
        wisdom = wisdom,
        charisma = charisma
    )

    @OptIn(ExperimentalUuidApi::class)
    fun fromDto(character: CharacterDto) = CharacterUi(
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

    fun getAttribute(attributeType: AttributeType): Int {
        return when (attributeType) {
            VITALITY -> vitality
            ENERGY -> energy
            STRENGTH -> strength
            ENDURANCE -> endurance
            DEXTERITY -> dexterity
            INTELLIGENCE -> intelligence
            WISDOM -> wisdom
            CHARISMA -> charisma
            else -> 0
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun setAttribute(attributeType: AttributeType, value: Int): CharacterUi {
        return when (attributeType) {
            VITALITY -> copy(vitality = value)
            ENERGY -> copy(energy = value)
            STRENGTH -> copy(strength = value)
            ENDURANCE -> copy(endurance = value)
            DEXTERITY -> copy(dexterity = value)
            INTELLIGENCE -> copy(intelligence = value)
            WISDOM -> copy(wisdom = value)
            CHARISMA -> copy(charisma = value)
            else -> this
        }
    }
}
