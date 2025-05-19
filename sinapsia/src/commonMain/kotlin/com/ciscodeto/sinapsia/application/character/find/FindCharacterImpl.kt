package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.application.character.find.FindCharacter
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.character.repository.toDomain
import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class FindCharacterImpl(
    private val repository: CharacterRepository
) : FindCharacter {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun findById(id: Uuid): Character? {
        return repository.findById(id)?.toDomain()
    }

    override suspend fun findByName(name: String): List<Character> {
        return repository.findAllByName(name).map { it.toDomain() }
    }
}