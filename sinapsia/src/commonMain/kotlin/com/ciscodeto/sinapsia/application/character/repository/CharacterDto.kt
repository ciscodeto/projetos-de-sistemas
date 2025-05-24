package com.ciscodeto.sinapsia.application.character.repository

import com.ciscodeto.sinapsia.domain.attributes.Attributes
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@OptIn(ExperimentalUuidApi::class)

data class CharacterDto(
    val id: Uuid,
    val name: String,
    val description: String,
    val age: Int,
    val level: Int,
    val experience: Int,
    val gold: Int,
    val currentHealth: Int,
    val currentEnergy: Int,
    val attributes: Attributes,
)