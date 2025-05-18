package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.domain.character.Character

interface FindAllCharacters {
    fun findAll(): List<Character>
}