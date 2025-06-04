package com.ciscodeto.app4reinos.scene.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import com.ciscodeto.app4reinos.scene.domain.ActionUi
import com.ciscodeto.app4reinos.scene.domain.CharacterUi
import com.ciscodeto.app4reinos.scene.presentation.components.cards.ActionSlot
import com.ciscodeto.app4reinos.scene.presentation.components.cards.CharacterSlot
import com.ciscodeto.app4reinos.scene.presentation.components.cards.EmptySlot
import com.ciscodeto.app4reinos.scene.presentation.viewmodels.BottomSheetMode
import com.ciscodeto.app4reinos.scene.presentation.viewmodels.SceneViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class, ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@Composable
fun SceneScreen(
    navController: NavHostController,
    sceneId: Uuid? = null
) {
    val viewModel = koinViewModel<SceneViewModel>()
    val actor by remember { derivedStateOf { viewModel.actor } }
    val action by remember { derivedStateOf { viewModel.action } } // ActionUi?
    val target by remember { derivedStateOf { viewModel.target } } // CharacterUi?
    val reaction by remember { derivedStateOf { viewModel.reaction } } // ActionUi?

    val bottomSheetMode by remember { derivedStateOf { viewModel.bottomSheetMode } }
    val selectableCharacters by viewModel.selectableCharacters.collectAsState()
    val selectableActions by viewModel.selectableActions.collectAsState()

    val isActorSelected by remember { derivedStateOf { actor != null } }
    val isActionSelected by remember { derivedStateOf { action != null } }
    val isTargetSelected by remember { derivedStateOf { target != null } }
    val isReactionSelected by remember { derivedStateOf { reaction != null } }

    ReinosScaffold (
        topBar = { ReinosAppBar(
            title = "Cena",
            canNavigateBack = true,
            navigateUp = { navController.navigateUp() }
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ) {
                CharacterSlot(
                    actor = actor,
                    onSelectCharacter = {
                        viewModel.initiateActorSelection() },
                    onRemoveCharacter = {
                        viewModel.removeActor() },
                    modifier = Modifier.weight(1f, false),
                )
                EmptySlot(
                    text = "Roll\nAction",
                    enabled = false,
                    modifier = Modifier
                        .weight(1f, false),
                    onClick = {  }
                )
                ActionSlot( // Slot da Ação
                    action = action,
                    onSelect = { viewModel.initiateActionSelection() },
                    onRemove = { viewModel.removeAction() },
                    enabled = isActorSelected,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ) {
                CharacterSlot( // Slot do Alvo
                    actor = target, // Usando CharacterUi para o alvo
                    onSelectCharacter = { viewModel.initiateTargetSelection() },
                    onRemoveCharacter = { viewModel.removeTarget() },
                    enabled = isActionSelected && (action?.requiresTarget == true),
                    modifier = Modifier.weight(1f, fill = false)
                )
                EmptySlot(
                    text = "Roll\nAction",
                    enabled = false,
                    modifier = Modifier
                        .weight(1f, false),
                    onClick = {  }
                )
                ActionSlot( // Slot da Ação
                    action = reaction,
                    onSelect = { viewModel.initiateReactionSelection() },
                    onRemove = { viewModel.removeReaction() },
                    enabled = isTargetSelected,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            ) {
                AnimatedContent(
                    targetState = bottomSheetMode,
                    transitionSpec = {
                        (slideInVertically(initialOffsetY = { it }) + fadeIn())
                            .togetherWith(fadeOut(animationSpec = tween(150)))
                    },
                    label = "BottomSheetContentAnimation"
                ) { mode ->
                    when (mode) {
                        BottomSheetMode.LOG -> {
                            // TODO: Implementar o Log da Cena aqui
                            Column(
                                modifier = Modifier.fillMaxSize().padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "Log da Cena aparecerá aqui.",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                // Futuramente, um LazyColumn com os eventos do log
                            }
                        }

                        BottomSheetMode.CHARACTER_SELECTION -> {
                            if (selectableCharacters.isEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Nenhum personagem para selecionar ou carregando...",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            } else {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(selectableCharacters) { character ->
                                        CharacterSelectItem(
                                            character = character,
                                            onClick = {
                                                viewModel.confirmActorSelection(character)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        BottomSheetMode.ACTION_SELECTION -> {
                            if (selectableActions.isEmpty()) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text("Nenhuma ação disponível ou carregando...", style = MaterialTheme.typography.bodyLarge)
                                }
                            } else {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(selectableActions, key = { it.id.toString() }) { actionItem ->
                                        ActionSelectItem(
                                            actionUi = actionItem,
                                            onClick = { viewModel.confirmActionSelection(actionItem) }
                                        )
                                    }
                                }
                            }
                        }

                        BottomSheetMode.REACTION_SELECTION -> {
                            if (selectableActions.isEmpty()) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text("Nenhuma ação disponível ou carregando...", style = MaterialTheme.typography.bodyLarge)
                                }
                            } else {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(selectableActions, key = { it.id.toString() }) { actionItem ->
                                        ActionSelectItem(
                                            actionUi = actionItem,
                                            onClick = { viewModel.confirmReactionSelection(actionItem) }
                                        )
                                    }
                                }
                            }
                        }

                        BottomSheetMode.TARGET_SELECTION -> {
                            if (selectableCharacters.isEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Nenhum personagem para selecionar ou carregando...",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            } else {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(selectableCharacters) { character ->
                                        CharacterSelectItem(
                                            character = character,
                                            onClick = {
                                                viewModel.confirmTargetSelection(character)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterSelectItem(
    character: CharacterUi, // Assegure que é com.ciscodeto.app4reinos.scene.domain.CharacterUi
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = character.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Lvl: ${character.level}", style = MaterialTheme.typography.bodyMedium)
        }
        HorizontalDivider()
    }
}

@Composable
fun ActionSelectItem(
    actionUi: ActionUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable(onClick = onClick).padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = actionUi.name, style = MaterialTheme.typography.titleMedium)
        }
        HorizontalDivider()
    }
}