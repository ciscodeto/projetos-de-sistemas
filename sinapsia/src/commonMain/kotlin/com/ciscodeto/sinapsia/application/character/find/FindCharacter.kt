package com.ciscodeto.application.character.find

import com.ciscodeto.domain.character.Character

interface FindCharacter {
    fun findById(id: String): Character?
    fun findByName(name: String): List<Character>
}