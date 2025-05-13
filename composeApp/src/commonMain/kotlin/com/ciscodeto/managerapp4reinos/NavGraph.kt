package com.ciscodeto.managerapp4reinos

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "home") {

    }
}