package com.ciscodeto.sinapsia.application.character.update

import com.ciscodeto.sinapsia.domain.attributes.Attributes

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface UpdateCharacter {
    suspend fun update(character: RequestModel) : ResponseModel

    data class ResponseModel(
        val id: String,
        val name: String,
        val description: String,
    )
    @OptIn(ExperimentalUuidApi::class)
    data class RequestModel(
        val id: Uuid,
        val name: String,
        val description: String,
        val age: Int,
        val level: Int,
        val experience: Int,
        val gold: Int,
        val health: Int,
        val stamina: Int,
        val attributes: Attributes,
    )
}