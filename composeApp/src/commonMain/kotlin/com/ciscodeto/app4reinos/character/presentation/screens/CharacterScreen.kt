package com.ciscodeto.app4reinos.character.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharacterViewModel
import com.ciscodeto.app4reinos.character.presentation.components.AttributeList
import com.ciscodeto.app4reinos.character.presentation.components.CharacterHeader
import com.ciscodeto.app4reinos.character.presentation.components.VitalStatSection
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.containers.RoundedContainer
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import org.koin.compose.viewmodel.koinViewModel

enum class CharacterScreenMode {
    VIEW, EDIT, CREATE
}

@Composable
fun CharacterScreen(
    navController: NavController,
    mode: CharacterScreenMode
) {
    val viewModel = koinViewModel<CharacterViewModel>()
    val scrollState = rememberScrollState()
    val character = viewModel.character

    ReinosScaffold(
        topBar = {
            ReinosAppBar(
                title = when (mode) {
                    CharacterScreenMode.VIEW -> "Personagem"
                    CharacterScreenMode.EDIT -> "Editar Personagem"
                    CharacterScreenMode.CREATE -> "Criar Personagem"
                },
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            CharacterHeader(
                name = character.name,
                level = character.level,
                editable = mode != CharacterScreenMode.VIEW
            )

            Spacer(modifier = Modifier.height(16.dp))

            VitalStatSection(
                title = "VITALIDADE",
                value = character.vitality,
                editable = mode != CharacterScreenMode.VIEW
            )
            VitalStatSection(
                title = "ENERGIA",
                value = character.energy,
                editable = mode != CharacterScreenMode.VIEW
            )

            Spacer(modifier = Modifier.height(16.dp))

            AttributeList(
                attributes = character.attributes,
                editable = mode != CharacterScreenMode.VIEW
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (mode != CharacterScreenMode.VIEW) {
                CharacterOptions()
            }
        }
    }
}

@Composable
fun CharacterOptions() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(3) {
            RoundedContainer { Text("Test") }
        }
    }
}