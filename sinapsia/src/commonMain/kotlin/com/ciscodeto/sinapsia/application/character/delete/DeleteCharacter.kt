package com.ciscodeto.sinapsia.application.character.delete

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface DeleteCharacter {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun delete(id: Uuid) : ResponseModel

    @OptIn(ExperimentalUuidApi::class)
    data class ResponseModel(
        val id: Uuid,
        val name: String,
    )
}