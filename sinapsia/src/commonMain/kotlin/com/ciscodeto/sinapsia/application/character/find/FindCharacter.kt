package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface FindCharacter {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun findById(id: Uuid): CharacterDto?
    suspend fun findByName(name: String): List<CharacterDto>
}