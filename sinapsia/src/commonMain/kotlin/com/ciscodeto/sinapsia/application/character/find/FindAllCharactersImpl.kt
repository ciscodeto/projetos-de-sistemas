package com.ciscodeto.sinapsia.application.character.find

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.character.repository.toDomain
import com.ciscodeto.sinapsia.domain.character.Character

class FindAllCharactersImpl(
    private val repository: CharacterRepository
) : FindAllCharacters {
    override suspend fun findAll(): List<Character> {
        return repository.findAll().map{ it.toDomain() }
    }
}