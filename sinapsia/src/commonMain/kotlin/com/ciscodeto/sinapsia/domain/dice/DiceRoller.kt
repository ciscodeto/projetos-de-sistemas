package com.ciscodeto.sinapsia.domain.dice

import kotlin.random.Random

/**
 * Um serviço/utilitário para simular rolagens de dados a partir de notação de texto.
 * Implementado como um 'object' para ser usado como um singleton (ex: DiceRoller.roll("1d20+5")).
 */
object DiceRoller {
    private val DICE_NOTATION_REGEX = """(\d+)?d(\d+)([+-]\d+)?""".toRegex()

    /**
     * Rola dados com base em uma notação de texto (ex: "1d20", "2d6", "1d8+5", "d20-1").
     * @param notation A string que representa a rolagem de dados.
     * @return A soma total da rolagem, incluindo modificadores. Retorna 0 se a notação for inválida.
     */
    fun roll(notation: String): Int {
        val matchResult = DICE_NOTATION_REGEX.matchEntire(notation.lowercase().trim())
            ?: return 0

        try {
            val numberOfDice = matchResult.groups[1]?.value?.toIntOrNull() ?: 1
            val sides = matchResult.groups[2]!!.value.toInt()
            val modifier = matchResult.groups[3]?.value?.toIntOrNull() ?: 0

            if (sides <= 0 || numberOfDice <= 0) return 0

            val diceRollsSum = (1..numberOfDice).sumOf {
                Random.nextInt(1, sides + 1)
            }

            return diceRollsSum + modifier
        } catch (e: NumberFormatException) {
            return 0
        }
    }
}