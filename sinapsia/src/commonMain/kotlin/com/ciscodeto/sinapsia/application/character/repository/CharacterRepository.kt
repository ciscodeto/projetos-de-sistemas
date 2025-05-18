package com.ciscodeto.sinapsia.application.character.repository

import com.ciscodeto.domain.character.Character

interface CharacterRepository {
    fun save(model: Character)
    fun update(model: Character)
    fun delete(id: String)
    fun findAll(): List<Character>
    fun findAllByName(name: String): List<Character>
    fun findById(id: String): Character?
}