package com.ciscodeto.sinapsia.infrastructure.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity (
    @PrimaryKey
    var id: ByteArray,
    var ownerId: String = "",
    var name: String = "",
    var description: String = "",
    var price: Int = 0,

    var vitality: Int = 0,
    var energy: Int = 0,
    var strength: Int = 0,
    var dexterity: Int = 0,
    var endurance: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ItemEntity

        return id.contentEquals(other.id)
    }

    override fun hashCode(): Int {
        return id.contentHashCode()
    }
}