package com.ciscodeto.application.character.update

import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.application.character.update.UpdateCharacter.*

class UpdateCharacterImpl(
    private val repository: CharacterRepository
) : UpdateCharacter {
    override fun update(character: RequestModel): ResponseModel {
        val characterId = character.id

        repository.findById(characterId) ?: throw Exception("Character not found")
        return ResponseModel(
            id = characterId,
            name = character.name,
            "null",
        )
    }
}