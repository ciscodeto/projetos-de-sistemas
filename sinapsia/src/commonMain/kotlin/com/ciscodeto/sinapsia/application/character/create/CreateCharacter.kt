package com.ciscodeto.sinapsia.application.character.create

import com.ciscodeto.sinapsia.domain.attributes.Attributes

interface CreateCharacter {
    suspend fun create(model: RequestModel): ResponseModel

    data class RequestModel(
        val name: String,
        val age: Int,
        val description: String,
        val level: Int,
        val experience: Int,
        val gold: Int,
        val currentHealth: Int,
        val currentEnergy: Int,
        val attributes: Attributes,
    )

    data class ResponseModel(
        val id: String,
        val name: String,
    )
}