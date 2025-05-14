package com.ciscodeto.application.character.create

import com.ciscodeto.application.character.create.CreateCharacter.*
import com.ciscodeto.domain.character.Attributes
import com.ciscodeto.domain.character.Character
import com.ciscodeto.domain.character.CharacterId
import com.ciscodeto.application.character.repository.CharacterRepository
import kotlin.uuid.ExperimentalUuidApi

class CreateCharacterImpl(
    private val repository: CharacterRepository
) : CreateCharacter {
    companion object {
        private const val HEALTH_PER_VITALITY = 10
        private const val BASE_HEALTH = 50
        private const val ENERGY_PER_ENERGY = 10
        private const val BASE_ENERGY = 50
    }

    @OptIn(ExperimentalUuidApi::class)
    override fun create(model: RequestModel): ResponseModel {

        val health = calculateHealth(model.attributes)
        val energy = calculateEnergy(model.attributes)

        val character = Character(
            id = CharacterId(),
            name = model.name,
            age = model.age,
            level = model.level,
            experience = model.experience,
            gold = model.gold,
            health = health,
            energy = energy,
            attributes = model.attributes,
            attributeModifier = model.attributeModifier,
            inventory = emptyList(),
            relationships = emptyMap()
        )

        return ResponseModel(
            id = character.id.toString(),
            name = character.name
        )
    }

    private fun calculateHealth(attrs: Attributes): Int {
        return attrs.vitality * HEALTH_PER_VITALITY + BASE_HEALTH
    }

    private fun calculateEnergy(attrs: Attributes): Int {
        return attrs.energy * ENERGY_PER_ENERGY + BASE_ENERGY
    }
}