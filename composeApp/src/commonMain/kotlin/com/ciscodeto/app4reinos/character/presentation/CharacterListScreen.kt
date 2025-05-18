package com.ciscodeto.app4reinos.character.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.character.presentation.components.CharacterListElement
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersListScreen(navController: NavController) {
    val viewModel = koinViewModel<CharactersListViewModel>()

    val mockCharacters = listOf(
        Character(name = "Elandor", level = 12),
        Character(name = "Myriam", level = 25),
        Character(name = "Thorgar", level = 8),
        Character(name = "Selene", level = 19),
        Character(name = "Kaelen", level = 30)
    )

    Scaffold(
        topBar = {
            ReinosAppBar(
                title = "Characters",
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Ação de adicionar personagem */ },
                containerColor = Color(0xFFD8C0A7),
                contentColor = Color.Black
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Character")
            }
        },
        containerColor = Color(0xFF1C120D)
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(mockCharacters) { character ->
                CharacterListElement(
                    name = character.name,
                    level = character.level,
                    onClick = {
                        // Navegar para a tela de detalhes
                    }
                )
            }
        }
    }
}

data class Character(
    val name: String,
    val level: Int
)
