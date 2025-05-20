package com.ciscodeto.sinapsia.application.item.repository

import com.ciscodeto.sinapsia.domain.item.Item
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ItemRepository {
    suspend fun save(item: ItemDto)
    suspend fun update(item: ItemDto)
    suspend fun delete(id: Uuid)
    suspend fun findAll(): List<ItemDto>
    suspend fun findById(id: Uuid): ItemDto?
}