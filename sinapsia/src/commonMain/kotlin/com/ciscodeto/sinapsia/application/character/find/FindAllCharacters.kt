package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.domain.character.Character
import kotlinx.coroutines.flow.Flow

interface FindAllCharacters {
    suspend fun findAll(): Flow<List<CharacterDto>>
}