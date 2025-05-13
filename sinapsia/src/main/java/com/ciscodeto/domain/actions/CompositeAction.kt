package com.ciscodeto.domain.actions

import com.ciscodeto.domain.character.Attribute
import com.ciscodeto.domain.character.AttributeType

class CompositeAction(
    override val name: String,
    override val cost: Int,
    override val requiresTarget: Boolean = true,
    override val isAggressive: Boolean = true,
    private val children: List<Action>
) : Action {
    override val successAttributes = emptyList<AttributeType>()
    override val effectAttributes = emptyList<AttributeType>()

    override fun execute(context: ActionContext): ActionResult {
        val logs = mutableListOf<String>()
        var overallSuccess = true
        var totalValue = 0
        for (action in children) {
            val result = action.execute(context)
            logs += result.message
            if (!result.success) overallSuccess = false
            totalValue += result.finalValue
        }
        return ActionResult(
            diceValue = 0,
            success = overallSuccess,
            message = logs.joinToString("; "),
            finalValue = totalValue,
            counterActions = emptyList()
        )
    }
}