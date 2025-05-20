package com.ciscodeto.app4reinos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ciscodeto.app4reinos.NavDestinations.*
import com.ciscodeto.app4reinos.character.presentation.screens.CharactersListScreen
import com.ciscodeto.app4reinos.character.presentation.screens.CharacterScreen
import com.ciscodeto.app4reinos.home.HomeScreen
import com.ciscodeto.app4reinos.item.ItemsListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    Surface {
        NavHost(
            navController = navController,
            startDestination = HomeScreen,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable<HomeScreen> {
                HomeScreen(navController) }

            composable<CharactersListScreen> {
                CharactersListScreen(navController) }

            composable<CharacterScreen> {
                CharacterScreen(navController) }

            composable<ItemsListScreen> { ItemsListScreen(navController) }
        }
    }
}