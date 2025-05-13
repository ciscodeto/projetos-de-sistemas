package com.ciscodeto.managerapp4reinos

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "4 Reinos - RPG Manager",
    ) {
        App()
    }
}