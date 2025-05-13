package com.ciscodeto.application.character.update

import com.ciscodeto.application.character.repository.CharacterRepository
import com.ciscodeto.application.character.update.UpdateCharacter.*
import com.ciscodeto.domain.character.Character

class UpdateCharacterImpl(
    private val repository: CharacterRepository
) : UpdateCharacter {
    override fun update(request: RequestModel): ResponseModel {
        val characterId = request.id

        repository.findById(characterId) ?: throw Exception("Character not found")
        return ResponseModel(
            id = characterId,
            name = request.name,
            "null",
        )
    }
}