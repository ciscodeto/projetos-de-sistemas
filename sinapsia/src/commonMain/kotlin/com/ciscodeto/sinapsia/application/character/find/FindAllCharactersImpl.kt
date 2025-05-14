package com.ciscodeto.application.character.find

import com.ciscodeto.application.character.repository.CharacterRepository
import com.ciscodeto.domain.character.Character

class FindAllCharactersImpl(
    private val repository: CharacterRepository
) : FindAllCharacters {
    override fun findAll(): List<Character> {
        return repository.findAll()
    }
}