package com.ciscodeto.sinapsia.application.action.find

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class FindActionImpl(
    private val repository: ActionRepository
) : FindAction {

    override suspend fun findById(id: Uuid): ActionDto? {
        return repository.getActionById(id)
    }

    override suspend fun findByName(name: String): List<ActionDto> {
        return emptyList()
    }


    override suspend fun findCounterActionsFor(id: Uuid): List<ActionDto> {
        return repository.getCounterActionsFor(id)
    }
}