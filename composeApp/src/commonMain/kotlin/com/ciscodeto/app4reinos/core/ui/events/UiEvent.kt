package com.ciscodeto.app4reinos.core.ui.events

sealed class UiEvent {
    data object ShowDeleteConfirmation : UiEvent()
    data object DeleteConfirmed : UiEvent()
}
