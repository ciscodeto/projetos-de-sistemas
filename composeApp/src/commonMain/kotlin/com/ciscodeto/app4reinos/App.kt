package com.ciscodeto.app4reinos

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ciscodeto.app4reinos.core.ui.theme.ReinosAppTheme
import com.ciscodeto.app4reinos.di.appModules
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    KoinApplication(
        application = { modules(appModules()) }
    ) {
        ReinosAppTheme { NavGraph(navController = navController) }
    }
}