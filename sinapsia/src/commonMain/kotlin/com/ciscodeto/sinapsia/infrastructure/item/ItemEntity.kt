package com.ciscodeto.sinapsia.infrastructure.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ItemEntity {
    @PrimaryKey
    var id: String = ""
    var ownerId: String = ""
    var name: String = ""
    var description: String = ""
    var price: Int = 0

    var vitality: Int = 0
    var energy: Int = 0
    var strength: Int = 0
    var dexterity: Int = 0
    var endurance: Int = 0
    var intelligence: Int = 0
    var wisdom: Int = 0
    var charisma: Int = 0
}