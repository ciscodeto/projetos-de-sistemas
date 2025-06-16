package com.ciscodeto.sinapsia.infrastructure

import androidx.room.TypeConverter
import com.ciscodeto.sinapsia.domain.actions.effects.EffectType
import com.ciscodeto.sinapsia.domain.attributes.AttributeType

class Converters {
    @TypeConverter
    fun fromAttributeList(value: List<AttributeType>): String =
        value.joinToString(",") { it.name }

    @TypeConverter
    fun toAttributeList(value: String): List<AttributeType> =
        if (value.isBlank()) emptyList() else value.split(",").map { AttributeType.valueOf(it) }

    @TypeConverter
    fun fromEffectList(value: List<EffectType>): String =
        value.joinToString(",") { it.name }

    @TypeConverter
    fun toEffectList(value: String): List<EffectType> =
        if (value.isBlank()) emptyList() else value.split(",").map { EffectType.valueOf(it) }
}