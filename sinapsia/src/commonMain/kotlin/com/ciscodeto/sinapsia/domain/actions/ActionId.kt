package com.ciscodeto.sinapsia.domain.actions

import com.ciscodeto.sinapsia.domain.shared.Identifier
import com.ciscodeto.sinapsia.domain.shared.Notification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ActionId(private val value: Uuid = Uuid.random()) : Identifier<Uuid> {
    override fun validate() = Notification()
    override fun value(): Uuid = value
    override fun toString() = value.toString()
}

@OptIn(ExperimentalUuidApi::class)
fun Uuid.toCharacterId() = ActionId(this)