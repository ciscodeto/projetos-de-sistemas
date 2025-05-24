package com.ciscodeto.sinapsia.infrastructure.item

import com.ciscodeto.sinapsia.application.item.repository.ItemDto
import com.ciscodeto.sinapsia.application.item.repository.ItemRepository
import com.ciscodeto.sinapsia.domain.item.Item
import com.ciscodeto.sinapsia.infrastructure.character.CharacterDao
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ItemRepositoryRoomImpl(
    private val dao: ItemDao,
) : ItemRepository {
    override suspend fun save(item: ItemDto) {
        dao.insert(item.toEntity())
    }

    override suspend fun update(item: ItemDto) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Uuid) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<ItemDto> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Uuid): ItemDto? {
        TODO("Not yet implemented")
    }
}