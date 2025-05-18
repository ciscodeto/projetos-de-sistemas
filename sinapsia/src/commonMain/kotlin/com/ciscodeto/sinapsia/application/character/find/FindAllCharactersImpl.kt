package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.domain.character.Character
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters

class FindAllCharactersImpl(
    private val repository: CharacterRepository
) : FindAllCharacters {
    override fun findAll(): List<Character> {
        return repository.findAll()
    }
}