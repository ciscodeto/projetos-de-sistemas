package com.ciscodeto.managerapp4reinos

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ciscodeto.managerapp4reinos.core.ui.theme.ReinosAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    ReinosAppTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("4 Reinos") }) }
        ) {
            NavGraph(navController = navController)
        }
    }
}