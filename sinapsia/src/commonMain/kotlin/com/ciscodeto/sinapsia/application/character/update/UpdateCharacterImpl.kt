package com.ciscodeto.sinapsia.application.character.update

import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.character.update.UpdateCharacter.*
import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import kotlin.uuid.ExperimentalUuidApi

class UpdateCharacterImpl(
    private val repository: CharacterRepository
) : UpdateCharacter {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun update(character: RequestModel): ResponseModel {
        val characterId = character.id

        repository.findById(characterId) ?: throw Exception("Character not found")

        val characterDto = CharacterDto(
            id = characterId,
            name = character.name,
            description = character.description,
            age = character.age,
            level = character.level,
            experience = character.experience,
            gold = character.gold,
            currentHealth = character.currentHealth,
            currentEnergy = character.currentEnergy,
            attributes = character.attributes,
        )

        repository.update(characterDto)

        return ResponseModel(
            id = characterId.toString(),
            name = character.name,
            description = character.description,
        )
    }
}