package com.ciscodeto.sinapsia.infrastructure.action

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ActionRepositoryImpl(private val dao: ActionDao) : ActionRepository {
    override suspend fun saveAction(action: ActionDto): ActionDto {
        val entity = action.toEntity()
        dao.insert(entity)
        return entity.toDto()
    }

    override suspend fun getActionById(id: Uuid): ActionDto? {
        return dao.getById(id.toByteArray())?.toDto()
    }

    override suspend fun getActionByName(name: String): ActionDto? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllActions(): Flow<List<ActionDto>> {
        return dao.getAll().map { entities ->
            entities.map { actionEntity ->
                val counterActionIds =
                    dao.getCounterActionIds(actionEntity.id).map { Uuid.fromByteArray(it) }
                actionEntity.toDto(counterActionIds)
            }
        }
    }

    override suspend fun deleteActionById(id: Uuid) {
        dao.deleteById(id.toByteArray())
    }

    override suspend fun getCounterActionsFor(id: Uuid): List<ActionDto> {
        val counterIds = dao.getCounterActionIds(id.toByteArray())
        if (counterIds.isEmpty()) return emptyList()

        return dao.getByIds(counterIds).map { it.toDto() }
    }

    override suspend fun updateAction(action: ActionDto): ActionDto {
        val entity = action.toEntity()
        dao.update(entity)
        return entity.toDto()
    }
}
