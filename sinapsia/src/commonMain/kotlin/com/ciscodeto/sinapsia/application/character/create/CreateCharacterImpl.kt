package com.ciscodeto.application.character.create

import com.ciscodeto.application.character.create.CreateCharacter.*
import com.ciscodeto.sinapsia.domain.character.Attributes
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.character.CharacterId
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
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
    override suspend fun create(model: RequestModel): ResponseModel {

        val health = calculateHealth(model.attributes)
        val stamina = calculateStamina(model.attributes)

        val character = Character(
            id = CharacterId(),
            name = model.name,
            age = model.age,
            level = model.level,
            experience = model.experience,
            gold = model.gold,
            health = health,
            stamina = stamina,
            attributes = model.attributes,
            attributeModifier = model.attributeModifier,
            inventory = emptyList(),
            description = model.description,
        )

        return ResponseModel(
            id = character.id.toString(),
            name = character.name
        )
    }

    private fun calculateHealth(attrs: Attributes): Int {
        return attrs.vitality * HEALTH_PER_VITALITY + BASE_HEALTH
    }

    private fun calculateStamina(attrs: Attributes): Int {
        return attrs.energy * ENERGY_PER_ENERGY + BASE_ENERGY
    }
}