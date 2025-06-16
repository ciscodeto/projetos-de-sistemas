package com.ciscodeto.sinapsia.application.action.find

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import kotlinx.coroutines.flow.Flow

interface FindAllActions {
    suspend fun invoke(): Flow<List<ActionDto>>
}