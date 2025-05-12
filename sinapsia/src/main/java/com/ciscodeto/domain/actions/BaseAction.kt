package com.ciscodeto.domain.actions

interface BaseAction {
    val name: String
    val cost: Int

    fun execute()
    fun counterActions()
}