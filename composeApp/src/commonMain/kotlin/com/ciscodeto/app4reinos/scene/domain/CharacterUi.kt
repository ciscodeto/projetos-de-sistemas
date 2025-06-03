package com.ciscodeto.app4reinos.scene.domain

import androidx.compose.ui.graphics.painter.Painter
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
data class CharacterUi (
    val id: Uuid? = null,
    val name: String = "BARTRAN",
    val level: Int = 0,
    val health: Int,
    val energy: Int,
    val stats: Map<String, Int>,
)