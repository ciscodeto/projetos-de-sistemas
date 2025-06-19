package com.ciscodeto.app4reinos.scene.domain

import com.ciscodeto.app4reinos.scene.presentation.enums.LogEntryType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class LogEntry(
    val id: Uuid = Uuid.random(),
    val text: String,
    val type: LogEntryType
)