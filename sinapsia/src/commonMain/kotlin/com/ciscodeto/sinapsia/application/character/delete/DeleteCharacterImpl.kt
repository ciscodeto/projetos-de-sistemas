package com.ciscodeto.sinapsia.application.character.delete

import com.ciscodeto.sinapsia.application.character.delete.DeleteCharacter
import com.ciscodeto.sinapsia.application.character.delete.DeleteCharacter.*
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class DeleteCharacterImpl(
    private val repository: CharacterRepository
) : DeleteCharacter {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun delete(id: Uuid): ResponseModel {
        val name = repository.delete(id)
        return ResponseModel(id, "Character $name deleted")
    }
}