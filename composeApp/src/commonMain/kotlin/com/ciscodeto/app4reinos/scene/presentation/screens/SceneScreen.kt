package com.ciscodeto.app4reinos.scene.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import com.ciscodeto.app4reinos.scene.presentation.components.cards.CharacterCard
import com.ciscodeto.app4reinos.scene.presentation.components.cards.SlotCard
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
    val actor = viewModel.actor
    val action = viewModel.action
    val target = viewModel.target
    val reaction = viewModel.reaction

    var isActorSelected by remember { mutableStateOf(false) }
    var isActionSelected by remember { mutableStateOf(false) }
    var isTargetSelected by remember { mutableStateOf(false) }
    var isReactionSelected by remember { mutableStateOf(false) }

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
                AnimatedContent(
                    targetState = actor,
                    transitionSpec = {
                        (
                            slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 300)
                            )
                                    + fadeIn(animationSpec = tween(300))
                                    + scaleIn(initialScale = 0.9f, animationSpec = tween(300))
                            ).togetherWith(
                            fadeOut(animationSpec = tween(200))
                        )
                    },
                    modifier = Modifier.weight(1f, fill = false)
                ) { actor ->
                    if (actor != null) {
                        CharacterCard(
                            character = actor,
                            onClick = { viewModel.selectActor() },
                            onRemove = {},
                            modifier = Modifier,
                        )
                    } else {
                        SlotCard(
                            enabled = true,
                            onClick = { viewModel.selectActor() }
                        )
                    }
                }
                SlotCard(
                    enabled = false,
                    modifier = Modifier
                        .weight(1f, false),
                    onClick = { viewModel.rollActorDice() }
                )
                SlotCard(
                    enabled = isActorSelected,
                    modifier = Modifier
                        .weight(1f, false),
                    onClick = { viewModel.selectAction() }
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ) {
                SlotCard(
                    modifier = Modifier
                        .weight(1f, false)
                )
                SlotCard(
                    modifier = Modifier
                        .weight(1f, false)
                )
                SlotCard(
                    modifier = Modifier
                        .weight(1f, false)
                )
            }
            Column(modifier = Modifier
                .weight(1f
                )
            ) {

            }
        }
    }
}

