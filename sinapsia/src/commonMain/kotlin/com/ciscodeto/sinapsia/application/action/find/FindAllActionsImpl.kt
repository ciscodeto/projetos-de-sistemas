package com.ciscodeto.sinapsia.application.action.find

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class FindAllActionsImpl(
    private val repository: ActionRepository
) : FindAllActions {
    override suspend fun invoke(): Flow<List<ActionDto>> {
        val allDtos = repository.getAllActions()
        return allDtos
    }
}