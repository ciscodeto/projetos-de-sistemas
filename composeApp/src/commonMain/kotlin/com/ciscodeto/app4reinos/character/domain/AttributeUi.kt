package com.ciscodeto.app4reinos.character.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class AttributeUi(
    val name: String,
    val type: AttributeType,
    val value: Int,
    val icon: ImageVector
)