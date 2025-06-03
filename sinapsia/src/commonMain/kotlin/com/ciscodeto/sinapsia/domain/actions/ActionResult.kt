package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.actions.Action

data class ActionResult(
    val success: Boolean,
    val finalValue: Int,
    val counterActions: List<Action> = emptyList(),
    val effects: List<Effect> = emptyList(),
)