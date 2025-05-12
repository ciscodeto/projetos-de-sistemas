package com.ciscodeto.domain.character

import com.ciscodeto.domain.shared.Identifier
import com.ciscodeto.domain.shared.Notification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class CharacterId(val value: Uuid = Uuid.random()) : Identifier<Uuid> {
    override fun validate() = Notification()
    override fun value(): Uuid = value
    override fun toString() = value.toString()
}