package com.ciscodeto.sinapsia.application.character.create

import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.shared.Notification

class CharacterCreationServiceImpl : CharacterCreationService {
    override fun getRemainingPoints(attributes: Attributes, level: Int): Int {
        return Character.remainingAttributePoints(attributes, level)
    }

    override fun getMaxHealth(vitality: Int): Int {
        return Character.calculateMaxHealth(vitality)
    }

    override fun getMaxEnergy(energy: Int): Int {
        return Character.calculateMaxEnergy(energy)
    }
}
