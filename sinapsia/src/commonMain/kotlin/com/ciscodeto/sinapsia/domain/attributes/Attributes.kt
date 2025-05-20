package com.ciscodeto.sinapsia.domain.attributes

data class Attributes(
    val vitality: Int = 0,
    val energy: Int = 0,
    val strength: Int = 0,
    val endurance: Int = 0,
    val dexterity: Int = 0,
    val intelligence: Int = 0,
    val wisdom: Int = 0,
    val charisma: Int = 0,
) {

    fun total(): Int = listOf(
        vitality, energy, strength, endurance,
        dexterity, intelligence, wisdom, charisma
    ).sum()

    operator fun plus(other: Attributes): Attributes = Attributes(
        vitality + other.vitality,
        energy + other.energy,
        strength + other.strength,
        endurance + other.endurance,
        dexterity + other.dexterity,
        intelligence + other.intelligence,
        wisdom + other.wisdom,
        charisma + other.charisma,
    )
}