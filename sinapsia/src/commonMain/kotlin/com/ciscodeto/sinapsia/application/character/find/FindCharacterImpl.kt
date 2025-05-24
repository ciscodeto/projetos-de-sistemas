package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.character.repository.toDomain
import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class FindCharacterImpl(
    private val repository: CharacterRepository
) : FindCharacter {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun findById(id: Uuid): CharacterDto? {
        return repository.findById(id)
    }

    override suspend fun findByName(name: String): List<CharacterDto> {
        return repository.findAllByName(name)
    }
}