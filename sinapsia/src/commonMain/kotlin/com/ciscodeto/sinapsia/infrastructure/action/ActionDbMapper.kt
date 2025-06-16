package com.ciscodeto.sinapsia.infrastructure.action

import com.ciscodeto.sinapsia.application.action.repository.ActionDto
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun ActionDto.toEntity() = ActionEntity(
    id = id.toByteArray(),
    name = name,
    healthCost = healthCost,
    energyCost = energyCost,
    goldCost = goldCost,
    requiresTarget = requiresTarget,
    requiresReaction = requiresReaction,
    successAttributes = successAttributes,
    effectAttributes = effectAttributes,
    effectsOnSuccess = effectsOnSuccess,
)

@OptIn(ExperimentalUuidApi::class)
fun ActionEntity.toDto(counterActionsIds: List<Uuid> = emptyList()) = ActionDto(
    id = Uuid.fromByteArray(id),
    name = name,
    healthCost = healthCost,
    energyCost = energyCost,
    goldCost = goldCost,
    requiresTarget = requiresTarget,
    requiresReaction = requiresReaction,
    successAttributes = successAttributes,
    effectAttributes = effectAttributes,
    effectsOnSuccess = effectsOnSuccess,
    counterActionsIds = counterActionsIds,
)