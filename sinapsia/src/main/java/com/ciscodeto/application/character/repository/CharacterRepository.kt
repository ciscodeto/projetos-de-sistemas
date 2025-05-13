package com.ciscodeto.application.character.repository

import com.ciscodeto.domain.character.Character

interface CharacterRepository {
    fun save(dto: CharacterDto)
    fun update(dto: CharacterDto)
    fun delete(id: String)
    fun findAll(): List<Character>
    fun findAllByName(name: String): List<Character>
    fun findById(id: String): Character?
}