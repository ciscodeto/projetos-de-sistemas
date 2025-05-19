@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package com.ciscodeto.sinapsia.infrastructure.character

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.domain.character.Attributes
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun CharacterEntity.toDto() = run {
    val attributes = Attributes(
        vitality = vitality,
        energy = energy,
        strength = strength,
        endurance = endurance,
        dexterity = dexterity,
        intelligence = intelligence,
        wisdom = wisdom,
        charisma = charisma,
    )
    CharacterDto(
        id = Uuid.fromByteArray(id),
        name = name,
        age = age,
        level = level,
        experience = experience,
        description = description,
        gold = gold,
        health = health,
        stamina = stamina,
        attributes = attributes,
    )
}

@OptIn(ExperimentalUuidApi::class)
fun CharacterDto.toEntity() = CharacterEntity(
    id = id.toByteArray(),
    name = name,
    age = age,
    level = level,
    experience = experience,
    description = description,
    gold = gold,

    health = health,
    stamina = stamina,

    vitality = attributes.vitality,
    energy = attributes.energy,
    strength = attributes.strength,
    endurance = attributes.endurance,
    dexterity = attributes.dexterity,
    intelligence = attributes.intelligence,
    wisdom = attributes.wisdom,
    charisma = attributes.charisma,
)
