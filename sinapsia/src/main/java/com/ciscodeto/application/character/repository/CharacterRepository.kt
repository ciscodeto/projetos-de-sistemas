package com.ciscodeto.application.character.repository

import com.ciscodeto.domain.character.Character

interface CharacterRepository {
    fun save(character: Character)
    fun update(character: Character)
    fun delete(id: String)
    fun findAll(): List<Character>
    fun findById(id: String): Character?
}