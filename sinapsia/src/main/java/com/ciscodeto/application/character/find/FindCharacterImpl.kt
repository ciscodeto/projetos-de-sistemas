package com.ciscodeto.application.character.find

import com.ciscodeto.application.character.repository.CharacterRepository
import com.ciscodeto.domain.character.Character

class FindCharacterImpl(
    private val repository: CharacterRepository
) : FindCharacter {

    override fun findById(id: String): Character? {
        return repository.findById(id)
    }

    override fun findByName(name: String): List<Character> {
        return repository.findAllByName(name)
    }
}