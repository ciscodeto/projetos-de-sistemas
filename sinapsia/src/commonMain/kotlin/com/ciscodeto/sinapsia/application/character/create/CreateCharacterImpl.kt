package com.ciscodeto.sinapsia.application.character.create

import com.ciscodeto.sinapsia.application.character.create.CreateCharacter.*
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.character.CharacterId
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.character.repository.toDto
import kotlin.uuid.ExperimentalUuidApi

class CreateCharacterImpl(
    private val repository: CharacterRepository
) : CreateCharacter {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun create(model: RequestModel): ResponseModel {
        val character = Character(
            id = CharacterId(),
            name = model.name,
            age = model.age,
            level = model.level,
            experience = model.experience,
            gold = model.gold,
            currentHealth = model.currentHealth,
            currentEnergy = model.currentEnergy,
            attributes = model.attributes,
            inventory = emptyList(),
            description = model.description,
        )

        repository.save(character.toDto())

        return ResponseModel(
            id = character.id.toString(),
            name = character.name
        )
    }
}