package com.ciscodeto.app4reinos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ciscodeto.app4reinos.NavDestinations.*
import com.ciscodeto.app4reinos.character.presentation.screens.CharacterScreen
import com.ciscodeto.app4reinos.character.presentation.screens.CharactersListScreen
import com.ciscodeto.app4reinos.home.HomeScreen
import com.ciscodeto.app4reinos.item.ItemsListScreen
import com.ciscodeto.app4reinos.scene.presentation.SceneScreen
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
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
                val args = it.toRoute<CharacterScreen>()
                CharacterScreen(
                    navController = navController,
                    characterId = args.characterId?.let { id -> Uuid.fromByteArray(id.toByteArrayBase64()) }
                )
            }

            composable<ItemsListScreen> { ItemsListScreen(navController) }

            composable<SceneScreen> { SceneScreen(navController) }
        }
    }
}