package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.actions.effects.Effect

data class ActionResult(
    val success: Boolean,
    val rollValue: Int = 0,
    val finalValue: Int,
    val counterActions: List<Action> = emptyList(),
    val effects: List<Effect> = emptyList(),
    val message: String = "",
)