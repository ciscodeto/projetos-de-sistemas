package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.attributes.Attributes
import com.ciscodeto.sinapsia.domain.character.Character

sealed interface Effect {
    data class Damage(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.currentHealth -= amount
            return "Dealt $amount points of damage"
        }
    }

    data class Heal(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.currentHealth += amount
            return "Healed $amount points of health"
        }
    }

    data class Energy(val amount: Int): Effect {
        override fun apply(target: Character): String {
            target.currentEnergy += amount
            return "Gained $amount energy"
        }
    }

    data class Drain(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.currentEnergy -= amount
            return "Drained $amount energy"
        }
    }

    data class Steal(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.gold -= amount
            return "Stealed $amount gold"
        }
    }

    data class Gold(val attributes: Attributes): Effect {
        override fun apply(target: Character) : String {
            target.attributes += attributes
            return "Gained $attributes"
        }
    }

    data object Miss: Effect {
        override fun apply(target: Character) : String = "Miss"
    }

    fun apply(target: Character) : String
}