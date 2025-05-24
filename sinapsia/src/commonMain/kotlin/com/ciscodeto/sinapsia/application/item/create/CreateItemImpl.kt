package com.ciscodeto.sinapsia.application.item.create

import com.ciscodeto.sinapsia.application.character.repository.toDto
import com.ciscodeto.sinapsia.application.item.create.CreateItem.*
import com.ciscodeto.sinapsia.application.item.repository.ItemRepository
import com.ciscodeto.sinapsia.application.item.repository.toDto
import com.ciscodeto.sinapsia.domain.item.Item
import com.ciscodeto.sinapsia.domain.item.ItemId
import kotlin.uuid.ExperimentalUuidApi

class CreateItemImpl(
    private val repository: ItemRepository
) : CreateItem {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun create(model: RequestModel): ResponseModel {
        val item = Item(
            id = ItemId(),
            name = model.name,
            description = model.description,
            attributes = model.attributes,
            price = model.price
        )

        repository.save(item.toDto())

        return ResponseModel(
            id = item.id.toString(),
            name = item.name
        )
    }
}