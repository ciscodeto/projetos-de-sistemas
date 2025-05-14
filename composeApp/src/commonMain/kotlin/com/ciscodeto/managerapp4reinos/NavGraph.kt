package com.ciscodeto.managerapp4reinos

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ciscodeto.managerapp4reinos.NavDestinations.*
import com.ciscodeto.managerapp4reinos.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen> {
            HomeScreen()
        }
    }
}