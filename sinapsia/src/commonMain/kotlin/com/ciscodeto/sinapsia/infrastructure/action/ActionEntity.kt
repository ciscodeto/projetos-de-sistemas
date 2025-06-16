package com.ciscodeto.sinapsia.infrastructure.action

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ciscodeto.sinapsia.domain.actions.effects.EffectType
import com.ciscodeto.sinapsia.domain.attributes.AttributeType

@Entity(tableName = "actions")
class ActionEntity(
    @PrimaryKey
    val id: ByteArray,
    val name: String,
    val healthCost: Int,
    val energyCost: Int,
    val goldCost: Int,
    val requiresTarget: Boolean,
    val requiresReaction: Boolean,
    val successAttributes: List<AttributeType>,
    val effectAttributes: List<AttributeType>,
    val effectsOnSuccess: List<EffectType>,
)
