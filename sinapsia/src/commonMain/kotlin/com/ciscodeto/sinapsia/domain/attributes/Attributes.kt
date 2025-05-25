package com.ciscodeto.sinapsia.domain.attributes

data class Attributes(
    val vitality: Int = 5,
    val energy: Int = 5,
    val strength: Int = -10,
    val endurance: Int = -10,
    val dexterity: Int = -10,
    val intelligence: Int = -10,
    val wisdom: Int = -10,
    val charisma: Int = -10,
) {

    fun total(): Int = listOf(
        vitality, energy, strength, endurance,
        dexterity, intelligence, wisdom, charisma
    ).sum()

    fun pointsSpentSince(base: Attributes = Attributes()): Int {
        return listOf(
            vitality - base.vitality,
            energy - base.energy,
            strength - base.strength,
            endurance - base.endurance,
            dexterity - base.dexterity,
            intelligence - base.intelligence,
            wisdom - base.wisdom,
            charisma - base.charisma
        ).sum()
    }

    fun isValidUpgradeFrom(previous: Attributes): Boolean {
        return vitality >= previous.vitality &&
                energy >= previous.energy &&
                strength >= previous.strength &&
                endurance >= previous.endurance &&
                dexterity >= previous.dexterity &&
                intelligence >= previous.intelligence &&
                wisdom >= previous.wisdom &&
                charisma >= previous.charisma
    }

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