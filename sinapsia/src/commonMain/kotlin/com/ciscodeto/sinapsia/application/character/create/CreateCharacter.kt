package com.ciscodeto.application.character.create

import com.ciscodeto.domain.character.Attributes

interface CreateCharacter {
    fun create(model: RequestModel): ResponseModel

    data class RequestModel(
        val name: String,
        val age: Int,
        val level: Int,
        val experience: Int,
        val gold: Int,
        val health: Int,
        val energy: Int,
        val attributes: Attributes,
        val attributeModifier: Attributes,
    )

    data class ResponseModel(
        val id: String,
        val name: String,
    )
}