package com.ciscodeto.app4reinos.character.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterDetailsScreen(
    characterId: String,
    viewModel: CharacterDetailViewModel = koinViewModel<CharacterDetailViewModel>(),
    navController: NavController,
) {
    Scaffold(
        topBar = {
            ReinosAppBar(
                title = "Personagem",
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() }
            ) }
    ) {
        Column {

        }
    }
}