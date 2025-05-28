package com.ciscodeto.app4reinos.scene.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold

@Composable
fun SceneScreen(navController: NavHostController) {
    ReinosScaffold (
        topBar = { ReinosAppBar(
            title = "Items",
            canNavigateBack = true,
            navigateUp = { navController.navigateUp() }
        ) }
    ){

    }
}