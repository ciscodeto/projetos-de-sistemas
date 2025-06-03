package com.ciscodeto.sinapsia.infrastructure.scene

import androidx.room.Entity

@Entity(
    tableName = "scene_character_cross_ref",
    primaryKeys = ["sceneId", "characterId"]
)
data class SceneCharacterCrossRef(
    val sceneId: ByteArray,
    val characterId: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SceneCharacterCrossRef

        if (!sceneId.contentEquals(other.sceneId)) return false
        if (!characterId.contentEquals(other.characterId)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sceneId.contentHashCode()
        result = 31 * result + characterId.contentHashCode()
        return result
    }
}