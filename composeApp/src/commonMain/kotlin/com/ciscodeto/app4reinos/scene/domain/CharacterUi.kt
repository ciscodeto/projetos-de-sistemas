package com.ciscodeto.app4reinos.scene.domain

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
data class CharacterUi (
    val id: Uuid? = null,
    val name: String = "BARTRAN",
    val level: Int = 0,
    val maxHealth: Int,
    val maxEnergy: Int,
    var currentHealth: Int,
    var currentEnergy: Int,
    val stats: Map<String, Int>,
)