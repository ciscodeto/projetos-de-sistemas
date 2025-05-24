package com.ciscodeto.sinapsia.application.item.find

import com.ciscodeto.sinapsia.application.item.repository.ItemRepository

class FindAllItemsImpl(
    private val repository: ItemRepository
) : FindAllItems {
}