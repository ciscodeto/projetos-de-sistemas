package com.ciscodeto.sinapsia.application.action.find

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class FindAllActionsImpl(
    private val repository: ActionRepository
) : FindAllActions {
    override suspend fun findAll(): Flow<List<ActionDto>> {
        return repository.getAllActions()
    }

    override suspend fun findCounterActionsFor(id: Uuid): List<ActionDto> {
        return repository.getCounterActionsFor(id)
    }
}