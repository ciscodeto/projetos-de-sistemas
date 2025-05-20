package com.ciscodeto.app4reinos.character.domain

data class CharacterUi(
    val name: String = "",
    val level: Int = 1,
    val vitality: Int = 50,
    val energy: Int = 50,
    val strength: Int = 10,
    val resistance: Int = 10,
    val agility: Int = 10,
    val intelligence: Int = 10,
    val wisdom: Int = 10,
    val charisma: Int = 10
) {
    fun attributes(): List<AttributeUi> = listOf(
        AttributeUi("FORÇA", strength),
        AttributeUi("RESISTÊNCIA", resistance),
        AttributeUi("AGILIDADE", agility),
        AttributeUi("INTELIGÊNCIA", intelligence),
        AttributeUi("SABEDORIA", wisdom),
        AttributeUi("CARISMA", charisma)
    )
}
