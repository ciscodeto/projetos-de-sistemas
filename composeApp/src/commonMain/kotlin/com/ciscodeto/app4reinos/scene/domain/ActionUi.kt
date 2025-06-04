package com.ciscodeto.app4reinos.scene.domain

import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ActionUi (
    val id: Uuid,
    val name: String,
    val cost: Int,
    val icon: ImageVector,
    val description: String,
    val requiresTarget: Boolean
)