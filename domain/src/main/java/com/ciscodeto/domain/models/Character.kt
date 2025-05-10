package com.ciscodeto.domain.models

data class Character(
    val name: String,
    val age: Int,
    val level: Int,
    val experience: Int,
    val gold: Int,
    val health: Int,
    val energy: Int,
    val attributes: List<Attribute>,
    val attributePoints: Int,
    val attributeModifier: List<Attribute>,
    val inventory: List<Item>,
    val relationships: Map<Character, String>,
)
