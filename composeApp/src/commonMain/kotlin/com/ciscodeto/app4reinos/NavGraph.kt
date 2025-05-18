package com.ciscodeto.app4reinos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ciscodeto.app4reinos.NavDestinations.*
import com.ciscodeto.app4reinos.character.presentation.CharactersListScreen
import com.ciscodeto.app4reinos.character.presentation.CreateCharacterScreen
import com.ciscodeto.app4reinos.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surfaceContainerLowest,
            MaterialTheme.colorScheme.surfaceContainer)
        )
    Surface {
        NavHost(
            navController = navController,
            startDestination = HomeScreen,
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient)
        ) {
            composable<HomeScreen> {
                HomeScreen(navController)
            }
            composable<CharactersListScreen> {
                CharactersListScreen(navController)
            }
            composable<CreateCharacterScreen> {
                CreateCharacterScreen()
            }
        }
    }
}