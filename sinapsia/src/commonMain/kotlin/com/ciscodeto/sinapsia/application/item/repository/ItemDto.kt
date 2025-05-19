package com.ciscodeto.sinapsia.application.item.repository

import com.ciscodeto.sinapsia.infrastructure.item.ItemEntity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ItemDto(
    val id: Uuid,
    val name: String,
    val description: String,
) {
    companion object {
        fun from(entity: ItemEntity) = ItemDto(
            id = Uuid.fromByteArray(entity.id),
            name = entity.name,
            description = entity.description,
        )
    }
}