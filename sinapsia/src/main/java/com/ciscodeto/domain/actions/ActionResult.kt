package com.ciscodeto.domain.actions

data class ActionResult(
    val diceValue: Int,
    val success: Boolean,
    val message: String,
    val finalValue: Int,
    val counterActions: List<Action> = emptyList(),
)