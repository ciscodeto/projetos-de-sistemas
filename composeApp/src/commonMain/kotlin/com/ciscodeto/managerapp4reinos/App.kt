package com.ciscodeto.managerapp4reinos

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ciscodeto.managerapp4reinos.core.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    AppTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("4 Reinos") }) }
        ) {
            NavGraph(navController = navController)
        }
    }
}