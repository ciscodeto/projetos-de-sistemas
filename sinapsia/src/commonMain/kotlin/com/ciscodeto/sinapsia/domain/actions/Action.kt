package com.ciscodeto.domain.actions

import com.ciscodeto.domain.character.AttributeType
import com.ciscodeto.sinapsia.domain.character.Attributes
import com.ciscodeto.sinapsia.domain.character.Character

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

    fun execute(attributes: Attributes, target: Character): ActionResult
}