package com.ciscodeto.sinapsia.application.character.create

import com.ciscodeto.sinapsia.domain.attributes.Attributes

interface CharacterCreationService {
    fun getRemainingPoints(attributes: Attributes, level: Int): Int
    fun getMaxHealth(vitality: Int): Int
    fun getMaxEnergy(energy: Int): Int
}