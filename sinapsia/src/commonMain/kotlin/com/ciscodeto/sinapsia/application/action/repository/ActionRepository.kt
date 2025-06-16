package com.ciscodeto.sinapsia.application.action.repository

import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ActionRepository {
    suspend fun saveAction(action: ActionDto): ActionDto
    suspend fun getActionById(id: Uuid): ActionDto?
    suspend fun getActionByName(name: String): ActionDto?
    suspend fun getCounterActionsFor(id: Uuid): List<ActionDto>
    suspend fun getAllActions(): Flow<List<ActionDto>>
    suspend fun deleteActionById(id: Uuid)
    suspend fun updateAction(action: ActionDto): ActionDto
}