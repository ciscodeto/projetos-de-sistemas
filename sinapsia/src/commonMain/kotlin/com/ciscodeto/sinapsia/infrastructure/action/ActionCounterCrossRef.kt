package com.ciscodeto.sinapsia.infrastructure.action

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "action_counter_cross_ref",
    primaryKeys = ["actionId", "counterActionId"],
    foreignKeys = [
        ForeignKey(
            entity = ActionEntity::class,
            parentColumns = ["id"],
            childColumns = ["actionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActionEntity::class,
            parentColumns = ["id"],
            childColumns = ["counterActionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["actionId"]),
        Index(value = ["counterActionId"])
    ]
)
data class ActionCounterCrossRef(
    val actionId: ByteArray,
    val counterActionId: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ActionCounterCrossRef

        if (!actionId.contentEquals(other.actionId)) return false
        if (!counterActionId.contentEquals(other.counterActionId)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = actionId.contentHashCode()
        result = 31 * result + counterActionId.contentHashCode()
        return result
    }
}

