package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.attributes.AttributeType
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.shared.Identifier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface Action{
    @OptIn(ExperimentalUuidApi::class)
    val id: Identifier<Uuid>
    val name: String
    val healthCost: Int
    val energyCost: Int
    val goldCost: Int
    val requiresTarget: Boolean
    val requiresReaction: Boolean
    /**
     * Quais atributos usar para teste de sucesso da ação
     */
    val successAttributes: List<AttributeType>
    /**
     * Quais atributos usar para calcular o efeito após sucesso
     */
    val effectAttributes: List<AttributeType>

    fun execute(attributes: Attributes, target: Character?): ActionResult
}