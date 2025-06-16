package com.ciscodeto.sinapsia.application.action.repository

import com.ciscodeto.sinapsia.domain.actions.effects.EffectType
import com.ciscodeto.sinapsia.domain.attributes.AttributeType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ActionDto(
    val id: Uuid,
    val name: String,
    val healthCost: Int,
    val energyCost: Int,
    val goldCost: Int,
    val requiresTarget: Boolean,
    val requiresReaction: Boolean,
    val successAttributes: List<AttributeType>,
    val effectAttributes: List<AttributeType>,
    val effectsOnSuccess: List<EffectType>,
    val counterActionsIds: List<Uuid>
)
