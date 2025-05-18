package com.ciscodeto.sinapsia.application.item.repository

import com.ciscodeto.domain.character.Item

interface ItemRepository {
    fun save(item: Item)
    fun update(item: Item)
    fun delete(id: String)
    fun findAll(): List<Item>
    fun findById(id: String): Item?
}