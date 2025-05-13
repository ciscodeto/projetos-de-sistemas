package com.ciscodeto.infrastructure.character

import com.ciscodeto.application.character.repository.CharacterDto
import com.ciscodeto.domain.character.Character
import kotlin.uuid.Uuid

fun CharacterDto.toCharacter() = Character(
    Uuid.toCharacterId(),
    name,
    description,
    age,
    level,
    experience,
    gold,
    health,
    energy,
    attributes,
)

fun Character.toDto() = CharacterDto(
    id,
    title,
    description,
    owner,
    collaborators,
)