package com.ciscodeto.domain.character

sealed class Attribute(open val value: Int) {
    data class Vitality(override val value: Int) : Attribute(value)
    data class Energy(override val value: Int) : Attribute(value)

    data class Strength(override val value: Int) : Attribute(value)
    data class Endurance(override val value: Int) : Attribute(value)
    data class Dexterity(override val value: Int) : Attribute(value)

    data class Intelligence(override val value: Int) : Attribute(value)
    data class Wisdom(override val value: Int) : Attribute(value)
    data class Charisma(override val value: Int) : Attribute(value)
}