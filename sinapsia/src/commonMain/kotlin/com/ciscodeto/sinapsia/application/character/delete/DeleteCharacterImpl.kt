package com.ciscodeto.application.character.delete

import com.ciscodeto.application.character.delete.DeleteCharacter.*
import com.ciscodeto.application.character.repository.CharacterRepository

class DeleteCharacterImpl(
    private val repository: CharacterRepository
) : DeleteCharacter {
    override fun delete(id: String): ResponseModel {
        val name = repository.delete(id)
        return ResponseModel(id, "Character $name deleted")
    }
}