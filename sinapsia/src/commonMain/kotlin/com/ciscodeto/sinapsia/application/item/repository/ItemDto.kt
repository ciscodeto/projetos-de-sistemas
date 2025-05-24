package com.ciscodeto.sinapsia.application.item.repository

import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.infrastructure.item.ItemEntity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ItemDto(
    val id: Uuid,
    val name: String,
    val price: Int,
    val description: String,
    val attributes: Attributes
)