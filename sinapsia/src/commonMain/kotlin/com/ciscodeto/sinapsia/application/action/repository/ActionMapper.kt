package com.ciscodeto.sinapsia.application.action.repository

import com.ciscodeto.sinapsia.domain.actions.Action
import com.ciscodeto.sinapsia.domain.actions.ActionId
import com.ciscodeto.sinapsia.domain.actions.ConfigurableAction
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun ActionDto.toDomain(counterActions: List<Action>) = ConfigurableAction(
    id = ActionId(id),
    name = name,
    healthCost = healthCost,
    energyCost = energyCost,
    goldCost = goldCost,
    requiresTarget = requiresTarget,
    requiresReaction = requiresReaction,
    successAttributes = successAttributes,
    effectAttributes = effectAttributes,
    effectsOnSuccess = effectsOnSuccess,
    counterActions = counterActions
)

@OptIn(ExperimentalUuidApi::class)
fun ConfigurableAction.toDto() = ActionDto(
    id = id.value(),
    name = name,
    healthCost = healthCost,
    energyCost = energyCost,
    goldCost = goldCost,
    requiresTarget = requiresTarget,
    requiresReaction = requiresReaction,
    successAttributes = successAttributes,
    effectAttributes = effectAttributes,
    effectsOnSuccess = effectsOnSuccess,
    counterActionsIds = counterActions.map { it.id.value() }
)
