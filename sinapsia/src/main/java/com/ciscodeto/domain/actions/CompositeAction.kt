package com.ciscodeto.domain.actions

class CompositeAction : BaseAction {
    private val actions = mutableListOf<BaseAction>()

    override fun execute() = actions.forEach { it.execute() }
    override fun counterActions() {}

    fun addAction(action: BaseAction) = actions.add(action)

    fun removeAction(action: BaseAction) = actions.remove(action)
}