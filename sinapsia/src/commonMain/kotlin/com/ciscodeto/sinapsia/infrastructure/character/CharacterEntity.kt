package com.ciscodeto.sinapsia.infrastructure.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    var id: ByteArray,

    var name: String = "",
    var age: Int = 0,
    var description: String = "",

    var level: Int = 0,
    var experience: Int = 0,
    var gold: Int = 0,

    var health: Int = 0,
    var stamina: Int = 0,

    var vitality: Int = 0,
    var energy: Int = 0,
    var strength: Int = 0,
    var dexterity: Int = 0,
    var endurance: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0,

    var createdAt: String = "",
    var modifiedAt: String = "",
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CharacterEntity

        return id.contentEquals(other.id)
    }

    override fun hashCode(): Int {
        return id.contentHashCode()
    }
}