package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.attributes.AttributeType
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.shared.Identifier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class CompositeAction(
    override val id: Identifier<Uuid>,
    override val name: String,
    override val healthCost: Int,
    override val energyCost: Int,
    override val goldCost: Int,
    override val requiresTarget: Boolean = true,
    override val requiresReaction: Boolean = true,
    private val children: List<Action>,
) : Action {
    override val successAttributes = emptyList<AttributeType>()
    override val effectAttributes = emptyList<AttributeType>()

    override fun execute(attributes: Attributes, target: Character?): ActionResult {
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