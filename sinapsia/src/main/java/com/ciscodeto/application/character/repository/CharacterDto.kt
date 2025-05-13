package com.ciscodeto.application.character.repository

data class CharacterDto(
    val id: String,
    val name: String,
    val description: String,
    val age: Int,
    val level: Int,
    val experience: Int,
    val gold: Int,
    val health: Int,
    val energy: Int,
    val attributes: List<AttributeDto>,
    val attributeModifier: List<AttributeDto>,
    val inventory: List<ItemDto>,
    val relationships: Map<CharacterDto, String>,
)

data class ItemDto(
    val id: String
)

class AttributeDto(
    val name: String
)
