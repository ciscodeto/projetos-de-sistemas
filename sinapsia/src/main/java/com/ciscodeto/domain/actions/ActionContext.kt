package com.ciscodeto.domain.actions

import com.ciscodeto.domain.shared.Entity

data class ActionContext(
    val actor: Entity,
    val targets: List<Entity>,
    val descriptors: List<Descriptor> = emptyList()
)