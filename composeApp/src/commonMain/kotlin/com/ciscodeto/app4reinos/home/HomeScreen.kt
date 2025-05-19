package com.ciscodeto.app4reinos.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ciscodeto.app4reinos.NavDestinations
import com.ciscodeto.app4reinos.NavDestinations.*
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import com.ciscodeto.app4reinos.core.components.buttons.GoldOutlinedButton
import org.jetbrains.compose.ui.tooling.preview.Preview

fun buttons() = mapOf(
    CharactersListScreen to "PERSONAGENS",
    ItemsListScreen to "ITENS"
)

@Composable
@Preview
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ReinosAppBar(title = "Home", canNavigateBack = false,) },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val options = buttons()
            options.forEach { label ->
                GoldOutlinedButton(
                    text = label.value,
                    onClick = { navController.navigate(label.key) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}