package com.ciscodeto.sinapsia.infrastructure.scene

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scenes")
data class SceneEntity(
    @PrimaryKey val id: ByteArray,
    val name: String = "",
    val description: String = "",
    val createdAt: String = "",
    val modifiedAt: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SceneEntity

        return id.contentEquals(other.id)
    }

    override fun hashCode(): Int {
        return id.contentHashCode()
    }
}
