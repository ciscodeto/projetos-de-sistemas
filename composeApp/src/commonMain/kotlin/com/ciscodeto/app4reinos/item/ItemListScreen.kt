package com.ciscodeto.app4reinos.item

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.layout.ReinosScaffold

@Composable
fun ItemsListScreen(navController: NavController) {
    ReinosScaffold (
        topBar = { ReinosAppBar(
            title = "Items",
            canNavigateBack = true,
            navigateUp = { navController.navigateUp() }
        ) }
    ){

    }
}