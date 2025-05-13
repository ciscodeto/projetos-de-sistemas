package com.ciscodeto.domain.actions

import com.ciscodeto.domain.character.Attribute
import com.ciscodeto.domain.character.AttributeType

interface Action {
    val name: String
    val cost: Int
    val requiresTarget: Boolean
    val isAggressive: Boolean
    /**
     * Quais atributos usar para teste de sucesso da ação
     */
    val successAttributes: List<AttributeType>
    /**
     * Quais atributos usar para calcular o efeito após sucesso
     */
    val effectAttributes: List<AttributeType>

    fun execute(context: ActionContext): ActionResult
}