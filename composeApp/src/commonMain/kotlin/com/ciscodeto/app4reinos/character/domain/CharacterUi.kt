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
    val level: Int = 0,

    val currentHealth: Int = 50,
    val currentEnergy: Int = 50,

    val attributes: AttributesUi = AttributesUi(
        vitality = 5,
        energy = 5,
        strength = -10,
        endurance = -10,
        dexterity = -10,
        intelligence = -10,
        wisdom = -10,
        charisma = -10
    )
) {

    fun allAttributesUi(): List<AttributeUi> = attributes.allAttributesUi()

    private fun vitalAttributesUi(): List<AttributeUi> = attributes.vitalAttributesUi()

    fun attributesUi(): List<AttributeUi> = attributes.attributesUi()

    fun attributes(): Attributes = Attributes(
        vitality = attributes.vitality,
        energy = attributes.energy,
        strength = attributes.strength,
        endurance = attributes.endurance,
        dexterity = attributes.dexterity,
        intelligence = attributes.intelligence,
        wisdom = attributes.wisdom,
        charisma = attributes.charisma
    )

    @OptIn(ExperimentalUuidApi::class)
    fun fromDto(character: CharacterDto) = CharacterUi(
        id = character.id,
        name = character.name,
        level = character.level,
        currentHealth = character.currentHealth,
        currentEnergy = character.currentEnergy,
        attributes = AttributesUi(
            vitality = character.attributes.vitality,
            energy = character.attributes.energy,
            strength = character.attributes.strength,
            endurance = character.attributes.endurance,
            dexterity = character.attributes.dexterity,
            intelligence = character.attributes.intelligence,
            wisdom = character.attributes.wisdom,
            charisma = character.attributes.charisma
        )
    )

    fun getAttribute(attributeType: AttributeType): Int {
        return when (attributeType) {
            VITALITY -> attributes.vitality
            ENERGY -> attributes.energy
            STRENGTH -> attributes.strength
            ENDURANCE -> attributes.endurance
            DEXTERITY -> attributes.dexterity
            INTELLIGENCE -> attributes.intelligence
            WISDOM -> attributes.wisdom
            CHARISMA -> attributes.charisma
            else -> 0
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun setAttribute(attributeType: AttributeType, value: Int): CharacterUi {
        val newAttributes = when (attributeType) {
            VITALITY -> attributes.copy(vitality = value)
            ENERGY -> attributes.copy(energy = value)
            STRENGTH -> attributes.copy(strength = value)
            ENDURANCE -> attributes.copy(endurance = value)
            DEXTERITY -> attributes.copy(dexterity = value)
            INTELLIGENCE -> attributes.copy(intelligence = value)
            WISDOM -> attributes.copy(wisdom = value)
            CHARISMA -> attributes.copy(charisma = value)
            else -> attributes
        }
        return when (attributeType) {
            VITALITY, ENERGY, STRENGTH, ENDURANCE, DEXTERITY, INTELLIGENCE, WISDOM, CHARISMA -> copy(attributes = newAttributes)
            else -> this
        }
    }
}
