package com.ciscodeto.application.board

class Dice(private val sides: Int) {
    fun roll(): Int{
        return (1..sides).random()
    }
}