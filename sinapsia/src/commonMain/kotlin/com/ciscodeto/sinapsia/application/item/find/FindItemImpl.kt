package com.ciscodeto.sinapsia.application.item.find

import com.ciscodeto.sinapsia.application.item.repository.ItemRepository

class FindItemImpl(
    val repository: ItemRepository
) : FindItem {
}