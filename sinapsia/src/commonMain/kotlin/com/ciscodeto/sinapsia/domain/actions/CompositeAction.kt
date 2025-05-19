package com.ciscodeto.domain.actions

import com.ciscodeto.domain.character.AttributeType
import com.ciscodeto.sinapsia.domain.character.Attributes
import com.ciscodeto.sinapsia.domain.character.Character

class CompositeAction(
    override val name: String,
    override val cost: Int,
    override val requiresTarget: Boolean = true,
    override val isAggressive: Boolean = true,
    private val children: List<Action>
) : Action {
    override val successAttributes = emptyList<AttributeType>()
    override val effectAttributes = emptyList<AttributeType>()

    override fun execute(attributes: Attributes, target: Character): ActionResult {
        val logs = mutableListOf<String>()
        var overallSuccess = true
        var totalValue = 0
        for (action in children) {
            val result = action.execute(attributes, target)
            if (!result.success) overallSuccess = false
            totalValue += result.finalValue
        }
        return ActionResult(
            success = overallSuccess,
            finalValue = totalValue,
            counterActions = emptyList()
        )
    }
}