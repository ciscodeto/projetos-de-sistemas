package com.ciscodeto.sinapsia.application.character.repository

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface CharacterRepository {
    suspend fun save(model: CharacterDto)
    suspend fun update(model: CharacterDto)
    @OptIn(ExperimentalUuidApi::class)
    suspend fun delete(id: Uuid)
    suspend fun findAll(): List<CharacterDto>
    suspend fun findAllByName(name: String): List<CharacterDto>
    @OptIn(ExperimentalUuidApi::class)
    suspend fun findById(id: Uuid): CharacterDto?
}