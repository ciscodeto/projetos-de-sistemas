package com.ciscodeto.sinapsia.application.item.repository

import com.ciscodeto.sinapsia.infrastructure.item.ItemEntity

data class ItemDto(
    val id: String,
    val name: String,
    val description: String,
) {
    companion object {
        fun from(entity: ItemEntity) = ItemDto(
            id = entity.id,
            name = entity.name,
            description = entity.description,
        )
    }
}