package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface FindCharacter {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun findById(id: Uuid): Character?
    suspend fun findByName(name: String): List<Character>
}