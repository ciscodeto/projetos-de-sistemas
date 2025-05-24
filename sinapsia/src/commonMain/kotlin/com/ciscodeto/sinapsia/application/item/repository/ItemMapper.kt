package com.ciscodeto.sinapsia.application.item.repository

import com.ciscodeto.sinapsia.domain.item.Item
import com.ciscodeto.sinapsia.domain.item.ItemId
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun Item.toDto() = ItemDto(
    id = id.value(),
    name = name,
    price = price,
    description = description,
    attributes = attributes,
)

@OptIn(ExperimentalUuidApi::class)
fun ItemDto.toDomain() = Item(
    id = ItemId(id),
    name = name,
    price = price,
    description = description,
    attributes = attributes,
)