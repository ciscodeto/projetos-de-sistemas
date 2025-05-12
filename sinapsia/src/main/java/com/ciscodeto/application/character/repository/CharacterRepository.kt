package com.ciscodeto.application.character.repository

import com.ciscodeto.domain.entities.character.Character

interface CharacterRepository {
    fun save(character: Character)
    fun update(character: Character)
    fun delete(character: Character)
    fun findAll(): List<Character>
    fun findById(id: String): Character?
}