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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharacterViewModel
import com.ciscodeto.app4reinos.character.presentation.components.AttributeRow
import com.ciscodeto.app4reinos.character.presentation.components.CharacterHeaderView
import com.ciscodeto.app4reinos.character.presentation.components.EditableCharacterHeader
import com.ciscodeto.app4reinos.character.presentation.components.VitalStatSection
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import org.koin.compose.viewmodel.koinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

enum class CharacterScreenMode {
    VIEW, EDIT, CREATE
}

@OptIn(ExperimentalUuidApi::class)
@Composable
fun CharacterScreen(
    navController: NavController,
    characterId: Uuid? = null
) {
    val viewModel = koinViewModel<CharacterViewModel>()
    val mode by viewModel.mode
    val character by viewModel.character
    val availablePoints by viewModel.availablePoints

    val scrollState = rememberScrollState()

    viewModel.init(characterId)

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
            if (mode == CharacterScreenMode.VIEW) {
                CharacterHeaderView(
                    name = character.name,
                    level = character.level,
                )
            } else {
                EditableCharacterHeader(
                    name = character.name,
                    level = character.level,
                    onNameChange = { viewModel.updateName(it) },
                    onLevelChange = { viewModel.updateLevel(it) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Pontos disponíveis: ${availablePoints}")

            RoundedColumn {
                AttributeRow(
                    name = "VITALIDADE",
                    value = character.vitality,
                    icon = Icons.Filled.Favorite,
                    editable = mode != CharacterScreenMode.VIEW,
                    onIncrease = { viewModel.increaseAttribute("VITALIDADE") },
                    onDecrease = { viewModel.decreaseAttribute("VITALIDADE") }
                )
                VitalStatSection(
                    currentValue = character.currentHealth,
                    value = character.vitality * 10,
                    foregroundColor = Color(0xFFC01D20)
                )
                AttributeRow(
                    name = "ENERGIA",
                    value = character.energy,
                    icon = Icons.Filled.Bolt,
                    editable = mode != CharacterScreenMode.VIEW,
                    onIncrease = { viewModel.increaseAttribute("ENERGIA") },
                    onDecrease = { viewModel.decreaseAttribute("ENERGIA") }
                )
                VitalStatSection(
                    currentValue = character.currentEnergy,
                    value = character.energy * 10,
                    foregroundColor = Color(0xFF22869A),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            RoundedColumn {
                character.attributesUi().forEach { (name, value, icon) ->
                    AttributeRow(
                        name = name,
                        value = value,
                        icon = icon,
                        editable = mode != CharacterScreenMode.VIEW,
                        onIncrease = { viewModel.increaseAttribute(name) },
                        onDecrease = { viewModel.decreaseAttribute(name) }
                    )
                } }

            Spacer(modifier = Modifier.height(16.dp))

            if (mode == CharacterScreenMode.CREATE) {
                Button(
                    onClick = { viewModel.createCharacter()
                                navController.navigateUp()},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Personagem")
                }
            }
            if (mode == CharacterScreenMode.EDIT) {
                Button(
                    onClick = { viewModel.updateCharacter()
                        navController.navigateUp()},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Personagem")
                }
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
            RoundedColumn { Text("Test") }
        }
    }
}