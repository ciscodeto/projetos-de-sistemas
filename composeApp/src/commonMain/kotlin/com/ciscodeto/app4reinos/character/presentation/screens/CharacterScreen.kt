package com.ciscodeto.app4reinos.character.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.character.domain.AttributeType.*
import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharacterViewModel
import com.ciscodeto.app4reinos.character.presentation.components.AttributeRow
import com.ciscodeto.app4reinos.character.presentation.components.CharacterHeaderView
import com.ciscodeto.app4reinos.character.presentation.components.EditableCharacterHeader
import com.ciscodeto.app4reinos.character.presentation.components.StatBar
import com.ciscodeto.app4reinos.character.presentation.screens.CharacterScreenMode.*
import com.ciscodeto.app4reinos.core.components.ConfirmSelectionDialog
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn
import com.ciscodeto.app4reinos.core.components.dropdownMenu.DropdownMenuInfo
import com.ciscodeto.app4reinos.core.components.dropdownMenu.DropdownOptions
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import com.ciscodeto.app4reinos.core.ui.events.UiEvent
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

    val mode = viewModel.mode
    val character = viewModel.character
    val availablePoints = viewModel.availablePoints

    val switchModeText = if (mode == VIEW) "Editar Personagem" else "Cancelar edição"
    val switchModeIcon = if (mode == VIEW) Icons.Filled.Edit else Icons.Filled.Cancel

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(characterId) {
        viewModel.init(characterId)
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowDeleteConfirmation -> {
                    showDeleteDialog = true
                }
                is UiEvent.DeleteConfirmed -> {
                    showDeleteDialog = false
                    navController.navigateUp()
                }
            }
        }
    }

    if (showDeleteDialog) {
        ConfirmSelectionDialog(
            title = "Deletar personagem?",
            message = "Tem certeza que deseja deletar este personagem? Essa ação não pode ser desfeita.",
            onConfirm = { viewModel.deleteCharacter() },
            onDismiss = { showDeleteDialog = false }
        )
    }

    ReinosScaffold(
        modifier = Modifier
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }
        ) { focusManager.clearFocus() },
        topBar = {
            ReinosAppBar(
                title = when (mode) {
                    VIEW -> "Personagem"
                    EDIT -> "Editar Personagem"
                    CREATE -> "Criar Personagem"
                },
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() },
                actions = { DropdownOptions(
                    items = if (mode != CREATE) {
                        listOf(
                            DropdownMenuInfo(
                                text = switchModeText,
                                leadingIcon = {
                                    Icon(
                                        imageVector = switchModeIcon,
                                        contentDescription = "$switchModeText Button",
                                        tint = Color(0xFFD6BFA1)
                                    )
                                },
                                onClick = { viewModel.switchMode() }
                            ),
                            DropdownMenuInfo(
                                text = "Delete Character",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Delete Character Button",
                                        tint = Color(0xFFD6BFA1)
                                    )
                                },
                                onClick = { viewModel.onDeleteClicked() }
                            )
                        )
                    } else {
                        emptyList()
                    }
                ) }
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
            if (mode == VIEW) {
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

            Text("Pontos disponíveis: $availablePoints")

            RoundedColumn {
                AttributeRow(
                    name = "VITALIDADE",
                    value = character.attributes.vitality,
                    currentValue = character.currentHealth,
                    currentMaxValue = viewModel.healthPerPoint * character.attributes.vitality,
                    icon = Icons.Filled.Favorite,
                    editable = mode != VIEW,
                    onValueChange = { viewModel.updateAttribute(VITALITY, it) },
                    onCurrentValueChange = { viewModel.updateCurrentHealth(it) },
                    onIncrease = { viewModel.increaseAttribute(VITALITY) },
                    onDecrease = { viewModel.decreaseAttribute(VITALITY) }
                )
                StatBar(
                    current = character.currentHealth,
                    total = viewModel.healthPerPoint * character.attributes.vitality,
                    foregroundColor = Color(0xFFC01D20)
                )
                AttributeRow(
                    name = "ENERGIA",
                    value = character.attributes.energy,
                    currentValue = character.currentEnergy,
                    currentMaxValue = viewModel.energyPerPoint * character.attributes.energy,
                    icon = Icons.Filled.Bolt,
                    editable = mode != VIEW,
                    onValueChange = { viewModel.updateAttribute(ENERGY, it) },
                    onCurrentValueChange = { viewModel.updateCurrentEnergy(it) },
                    onIncrease = { viewModel.increaseAttribute(ENERGY) },
                    onDecrease = { viewModel.decreaseAttribute(ENERGY) }
                )
                StatBar(
                    current = character.currentEnergy,
                    total = viewModel.energyPerPoint * character.attributes.energy,
                    foregroundColor = Color(0xFF22869A),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            RoundedColumn {
                character.attributesUi().forEach { (name, type, value, icon) ->
                    AttributeRow(
                        name = name,
                        value = value,
                        icon = icon,
                        editable = mode != VIEW,
                        onValueChange = { viewModel.updateAttribute(type, it) },
                        onIncrease = { viewModel.increaseAttribute(type) },
                        onDecrease = { viewModel.decreaseAttribute(type) }
                    )
                } }

            Spacer(modifier = Modifier.height(16.dp))

            if (mode == CREATE) {
                Button(
                    onClick = { viewModel.createCharacter()
                                navController.navigateUp()},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Personagem")
                }
            }
            if (mode == EDIT) {
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