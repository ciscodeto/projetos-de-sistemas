package com.ciscodeto.app4reinos.scene.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold
import com.ciscodeto.app4reinos.scene.presentation.components.cards.SlotCard
import com.ciscodeto.app4reinos.scene.presentation.viewmodels.SceneViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun SceneScreen(
    navController: NavHostController,
    sceneId: Uuid? = null
) {
    val viewModel = koinViewModel<SceneViewModel>()

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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SlotCard(modifier = Modifier.weight(1f))
                SlotCard(modifier = Modifier.weight(1f))
                SlotCard(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SlotCard(modifier = Modifier.weight(1f))
                SlotCard(modifier = Modifier.weight(1f))
                SlotCard(modifier = Modifier.weight(1f))
            }
        }
    }
}