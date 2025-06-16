package com.ciscodeto.sinapsia.application.action.find

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface FindAction {
    suspend fun findById(id: Uuid): ActionDto?
    suspend fun findByName(name: String): List<ActionDto>
    suspend fun findCounterActionsFor(id: Uuid): List<ActionDto>
}