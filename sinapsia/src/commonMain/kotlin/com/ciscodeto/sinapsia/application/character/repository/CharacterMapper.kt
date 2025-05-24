package com.ciscodeto.sinapsia.application.character.repository

import com.ciscodeto.sinapsia.domain.character.CharacterId
import com.ciscodeto.sinapsia.domain.character.Character
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun Character.toDto() = CharacterDto(
    id = id.value(),
    name = name,
    description = description,
    age = age,
    level = level,
    experience = experience,
    gold = gold,
    currentHealth = currentHealth,
    currentEnergy = currentEnergy,
    attributes = attributes,
)

@OptIn(ExperimentalUuidApi::class)
fun CharacterDto.toDomain() = Character(
    id = CharacterId(id),
    name = name,
    description = description,
    age = age,
    level = level,
    experience = experience,
    gold = gold,
    currentHealth = currentHealth,
    currentEnergy = currentEnergy,
    attributes = attributes,
    attributeModifier = null
)