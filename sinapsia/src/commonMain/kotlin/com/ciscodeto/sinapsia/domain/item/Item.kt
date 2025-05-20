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
    val age: Int,
    var level: Int,
    var experience: Int,
    var health: Int,
    var stamina: Int,
    var attributes: Attributes,
) : Entity<Uuid>(id) {

}