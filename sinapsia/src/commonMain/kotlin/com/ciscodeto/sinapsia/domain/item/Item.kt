package com.ciscodeto.sinapsia.domain.item

import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.shared.Entity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Item(
    id: ItemId,
    val name: String,
    val description: String,
    val price: Int,
    val age: Int = 0,
    var level: Int = 0,
    var experience: Int = 0,
    var currentHealth: Int = 0,
    var currentEnergy: Int = 0,
    var attributes: Attributes,
) : Entity<Uuid>(id) {

}