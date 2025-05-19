package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.domain.character.Character

interface FindAllCharacters {
    suspend fun findAll(): List<Character>
}