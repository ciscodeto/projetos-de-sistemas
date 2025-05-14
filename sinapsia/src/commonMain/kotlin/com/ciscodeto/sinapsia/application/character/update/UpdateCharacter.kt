package com.ciscodeto.application.character.update

import com.ciscodeto.domain.character.Attributes
import com.ciscodeto.domain.character.Item

import com.ciscodeto.domain.character.Character

interface UpdateCharacter {
    fun update(character: RequestModel) : ResponseModel

    data class ResponseModel(
        val id: String,
        val name: String,
        val description: String,
    )

    data class RequestModel(
        val id: String,
        val name: String,
        val description: String,
        val age: Int,
        val level: Int,
        val experience: Int,
        val gold: Int,
        val health: Int,
        val energy: Int,
        val attributes: Attributes,
        val attributeModifier: Attributes,
        val inventory: List<Item>,
        val relationships: Map<Character, String>,
    )
}