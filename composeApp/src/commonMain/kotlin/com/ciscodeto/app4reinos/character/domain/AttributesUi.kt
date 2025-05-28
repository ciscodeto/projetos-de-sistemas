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
import com.ciscodeto.sinapsia.domain.attributes.Attributes

data class AttributesUi(
    val vitality: Int,
    val energy: Int,
    val strength: Int,
    val endurance: Int,
    val dexterity: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
) {

    fun allAttributesUi(): List<AttributeUi> = vitalAttributesUi() + attributesUi()

    fun vitalAttributesUi(): List<AttributeUi> = listOf(
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
}