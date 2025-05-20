package com.ciscodeto.sinapsia.application.character.create

import com.ciscodeto.sinapsia.domain.attributes.Attributes

interface CreateCharacter {
    suspend fun create(model: RequestModel): ResponseModel

    data class RequestModel(
        val name: String,
        val age: Int,
        val level: Int,
        val experience: Int,
        val gold: Int,
        val attributes: Attributes,
        val attributeModifier: Attributes,
        val description: String,
    )

    data class ResponseModel(
        val id: String,
        val name: String,
    )
}