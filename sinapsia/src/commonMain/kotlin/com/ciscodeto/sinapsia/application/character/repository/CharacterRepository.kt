package com.ciscodeto.sinapsia.application.character.repository

import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface CharacterRepository {
    suspend fun save(model: CharacterDto)
    suspend fun update(model: CharacterDto)
    suspend fun delete(id: Uuid)
    suspend fun findAll(): Flow<List<CharacterDto>>
    suspend fun findAllByName(name: String): List<CharacterDto>
    suspend fun findById(id: Uuid): CharacterDto?
}