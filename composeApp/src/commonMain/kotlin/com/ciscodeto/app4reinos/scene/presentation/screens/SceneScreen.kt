package com.ciscodeto.app4reinos.scene.presentation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import com.ciscodeto.app4reinos.core.components.list.TopBorderListContainer
import com.ciscodeto.app4reinos.scene.domain.ActionUi
import com.ciscodeto.app4reinos.scene.domain.CharacterUi
import com.ciscodeto.app4reinos.scene.domain.LogEntry
import com.ciscodeto.app4reinos.scene.presentation.components.cards.ActionSlot
import com.ciscodeto.app4reinos.scene.presentation.components.cards.CharacterSlot
import com.ciscodeto.app4reinos.scene.presentation.components.cards.DiceRollCard
import com.ciscodeto.app4reinos.scene.presentation.enums.BottomSheetMode
import com.ciscodeto.app4reinos.scene.presentation.enums.LogEntryType
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
    val action by remember { derivedStateOf { viewModel.action } }
    val target by remember { derivedStateOf { viewModel.target } }
    val reaction by remember { derivedStateOf { viewModel.reaction } }

    val bottomSheetMode by remember { derivedStateOf { viewModel.bottomSheetMode } }
    val selectableCharacters by viewModel.selectableCharacters.collectAsState()
    val selectableActions by viewModel.selectableActions.collectAsState()
    val gameLog by viewModel.gameLog.collectAsState()

    val isActorSelected by remember { derivedStateOf { actor != null } }
    val isActionSelected by remember { derivedStateOf { action != null } }
    val isTargetSelected by remember { derivedStateOf { target != null } }
    val canSelectReaction by remember { derivedStateOf { viewModel.canSelectReaction } }
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
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
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
                DiceRollCard(
                    onRollClicked = { viewModel.rollAction() },
                    onEditClicked = {  },
                    enabled = isTargetSelected,
                    modifier = Modifier.weight(1f, fill = false)
                )
                ActionSlot(
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
                CharacterSlot(
                    actor = target,
                    onSelectCharacter = { viewModel.initiateTargetSelection() },
                    onRemoveCharacter = { viewModel.removeTarget() },
                    enabled = isActionSelected && (action?.requiresTarget == true),
                    modifier = Modifier.weight(1f, fill = false)
                )
                DiceRollCard(
                    onRollClicked = { viewModel.rollReaction() },
                    onEditClicked = {  },
                    enabled = isReactionSelected,
                    modifier = Modifier.weight(1f, fill = false)
                )
                ActionSlot(
                    action = reaction,
                    onSelect = { viewModel.initiateReactionSelection() },
                    onRemove = { viewModel.removeReaction() },
                    enabled = canSelectReaction,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            ) {
                when (bottomSheetMode) {
                    BottomSheetMode.LOG -> {
                        val lazyListState = rememberLazyListState()

                        // Efeito para rolar para o final sempre que a lista de log mudar
                        LaunchedEffect(gameLog.size) {
                            if (gameLog.isNotEmpty()) {
                                lazyListState.animateScrollToItem(index = gameLog.size - 1)
                            }
                        }

                        TopBorderListContainer(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                                .padding(top = 8.dp)
                        ) {
                            LazyColumn(state = lazyListState) {
                                items(gameLog, key = { it.id }) { logEntry ->
                                    LogEntryItem(logEntry)
                                    Spacer(modifier = Modifier.height(4.dp))
                                }
                            }
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
                            TopBorderListContainer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 8.dp)
                            ) {
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
                    }

                    BottomSheetMode.ACTION_SELECTION -> {
                        if (selectableActions.isEmpty()) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Nenhuma ação disponível ou carregando...", style = MaterialTheme.typography.bodyLarge)
                            }
                        } else {
                            TopBorderListContainer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 8.dp)
                            ) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(
                                        selectableActions,
                                        key = { it.id.toString() }) { actionItem ->
                                        ActionSelectItem(
                                            actionUi = actionItem,
                                            onClick = {
                                                viewModel.confirmActionSelection(
                                                    actionItem
                                                )
                                            }
                                        )
                                    }
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
                            TopBorderListContainer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 8.dp)
                            ) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(
                                        selectableActions,
                                        key = { it.id.toString() }) { actionItem ->
                                        ActionSelectItem(
                                            actionUi = actionItem,
                                            onClick = {
                                                viewModel.confirmReactionSelection(
                                                    actionItem
                                                )
                                            }
                                        )
                                    }
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
                            TopBorderListContainer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 8.dp)
                            ) {
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
    character: CharacterUi,
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
            Text(text = character.name, style = MaterialTheme.typography.bodyMedium)
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
            Text(text = actionUi.name, style = MaterialTheme.typography.bodyMedium)
        }
        HorizontalDivider()
    }
}

@Composable
fun LogEntryItem(logEntry: LogEntry) {
    val icon: ImageVector
    val color: Color
    val fontWeight: FontWeight?

    when (logEntry.type) {
        LogEntryType.INFO -> {
            icon = Icons.Default.Info
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            fontWeight = FontWeight.Normal
        }
        LogEntryType.ACTION -> {
            icon = Icons.Default.Star
            color = Color(0xFFFBC02D) // Amarelo
            fontWeight = FontWeight.SemiBold
        }
        LogEntryType.SUCCESS -> {
            icon = Icons.Default.CheckCircle
            color = Color(0xFF7CB342) // Verde
            fontWeight = FontWeight.Normal
        }
        LogEntryType.FAILURE -> {
            icon = Icons.Default.Dangerous
            color = Color(0xFFE53935) // Vermelho
            fontWeight = FontWeight.Normal
        }
        LogEntryType.DAMAGE -> {
            icon = Icons.Default.Whatshot
            color = Color(0xFFE53935) // Vermelho
            fontWeight = FontWeight.SemiBold
        }
        LogEntryType.REACTION -> {
            icon = Icons.Default.Shield
            color = Color(0xFF1E88E5) // Azul
            fontWeight = FontWeight.SemiBold
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = logEntry.type.name,
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = logEntry.text,
            color = color,
            fontWeight = fontWeight,
            fontSize = 14.sp
        )
    }
}