package com.ciscodeto.sinapsia.infrastructure.character

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class CharacterRepositoryRoomImpl(
    private val dao: CharacterDao,
) : CharacterRepository {
    override suspend fun save(model: CharacterDto) {
        dao.insert(model.toEntity())
    }

    override suspend fun update(model: CharacterDto) {
        dao.upsert(model.toEntity())
    }

    @ExperimentalUuidApi
    override suspend fun delete(id: Uuid) {
        dao.delete(id.toByteArray())
    }

    override suspend fun findAll(): List<CharacterDto> {
        return dao.getAll().map { it.toDto() }
    }

    override suspend fun findAllByName(name: String): List<CharacterDto> {
        return dao.getByName(name).map { it.toDto() }}

    @ExperimentalUuidApi
    override suspend fun findById(id: Uuid): CharacterDto? {
        return dao.getById(id.toByteArray())?.toDto()
    }
}