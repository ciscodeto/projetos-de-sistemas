package com.ciscodeto.sinapsia.application.item.create

import com.ciscodeto.sinapsia.domain.attributes.Attributes

interface CreateItem {
    suspend fun create(model: RequestModel): ResponseModel

    data class RequestModel(
        val name: String,
        val age: Int,
        val price: Int,
        val description: String,
        val attributes: Attributes,
    )

    data class ResponseModel(
        val id: String,
        val name: String,
    )
}