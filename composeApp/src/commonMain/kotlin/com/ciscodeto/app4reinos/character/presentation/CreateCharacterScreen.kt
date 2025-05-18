package com.ciscodeto.app4reinos.character.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateCharacterScreen() {
    val viewModel = koinViewModel<CreateCharacterViewModel>()

    Scaffold {
        LazyColumn() {

        }
    }
}