package com.ciscodeto.domain.actions

data class ActionResult(
    val success: Boolean,
    val finalValue: Int,
    val counterActions: List<Action> = emptyList(),
)