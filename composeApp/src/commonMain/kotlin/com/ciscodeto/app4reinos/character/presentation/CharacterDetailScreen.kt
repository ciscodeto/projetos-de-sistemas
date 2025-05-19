package com.ciscodeto.app4reinos.character.presentation

import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterDetailsScreen(
    characterId: String,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {
}