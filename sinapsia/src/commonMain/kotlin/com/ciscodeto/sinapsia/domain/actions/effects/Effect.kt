package com.ciscodeto.sinapsia.domain.actions.effects

import com.ciscodeto.sinapsia.domain.character.Character

sealed interface Effect {
    data class Damage(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.currentHealth -= amount
            return "Recebeu $amount dano"
        }
    }

    data class Heal(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.currentHealth += amount
            return "Curou $amount pontos de vida"
        }
    }

    data class Energy(val amount: Int): Effect {
        override fun apply(target: Character): String {
            target.currentEnergy += amount
            return "Ganhou $amount pontos de energia"
        }
    }

    data class Drain(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.currentEnergy -= amount
            return "Perdeu $amount pontos energia"
        }
    }

    data class Steal(val amount: Int): Effect {
        override fun apply(target: Character) : String {
            target.gold -= amount
            return "Perdeu $amount de ouro"
        }
    }

    data class Gold(val gold: Int): Effect {
        override fun apply(target: Character) : String {
            target.gold += gold
            return "Ganhou $gold ouro"
        }
    }

    data object Miss: Effect {
        override fun apply(target: Character) : String = "Miss"
    }

    fun apply(target: Character) : String
}