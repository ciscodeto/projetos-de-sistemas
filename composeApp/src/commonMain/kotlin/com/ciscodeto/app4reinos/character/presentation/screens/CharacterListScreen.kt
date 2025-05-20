package com.ciscodeto.app4reinos.character.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.NavDestinations.*
import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharactersListViewModel
import com.ciscodeto.app4reinos.character.presentation.components.CharacterListElement
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import com.ciscodeto.app4reinos.toBase64String
import org.koin.compose.viewmodel.koinViewModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun CharactersListScreen(
    navController: NavController,
    viewModel: CharactersListViewModel = koinViewModel()
) {
    val characters by viewModel.characters.collectAsState()

    ReinosScaffold(
        topBar = {
            ReinosAppBar(
                title = "Characters",
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(CharacterScreen()) },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.Black
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Character")
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(characters) { character ->
                CharacterListElement(
                    name = character.name,
                    level = character.level,
                    onClick = { navController
                        .navigate(CharacterScreen(character.id.toByteArray().toBase64String()))
                    }
                )
            }
        }
    }
}