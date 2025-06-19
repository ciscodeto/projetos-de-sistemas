package com.ciscodeto.sinapsia.application.action.find

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface FindAllActions {
    suspend fun findAll(): Flow<List<ActionDto>>
    @OptIn(ExperimentalUuidApi::class)
    suspend fun findCounterActionsFor(id: Uuid): List<ActionDto>
}