package com.ciscodeto.sinapsia.infrastructure.item

import com.ciscodeto.sinapsia.application.character.repository.CharacterDto
import com.ciscodeto.sinapsia.application.item.repository.ItemDto
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
fun ItemDto.toEntity() = ItemEntity(
    id = id.toByteArray(),
    ownerId = "",
    name = name,
    description = description,
    price = price,
    vitality = attributes.vitality,
    energy = attributes.energy,
    strength = attributes.strength,
    dexterity = attributes.dexterity,
    endurance = attributes.endurance,
    intelligence = attributes.intelligence,
    wisdom = attributes.wisdom,
    charisma = attributes.charisma
)

@OptIn(ExperimentalUuidApi::class)
fun ItemEntity.toDto() = run {
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
    ItemDto(
        id = Uuid.fromByteArray(id),
        name = name,
        price = price,
        description = description,
        attributes = attributes,
    )
}