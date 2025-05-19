package com.ciscodeto.app4reinos.character.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ciscodeto.app4reinos.character.presentation.components.AttributeList
import com.ciscodeto.app4reinos.character.presentation.components.CharacterHeader
import com.ciscodeto.app4reinos.character.presentation.components.VitalStatSection
import com.ciscodeto.app4reinos.core.components.bar.ReinosAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateCharacterScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<CreateCharacterViewModel>()
    val scrollState = rememberScrollState()

    CreateCharacter

    Scaffold(
        topBar = {
            ReinosAppBar(
                title = "Character",
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            CharacterHeader()

            Spacer(modifier = Modifier.height(16.dp))

            VitalStatSection(title = "VITALIDADE", value = 99)
            VitalStatSection(title = "ENERGIA", value = 99)

            Spacer(modifier = Modifier.height(16.dp))

            AttributeList(attributes = listOf(
                "FORÇA", "RESISTÊNCIA", "AGILIDADE",
                "INTELIGÊNCIA", "SABEDORIA", "CARISMA"
            ))

            Spacer(modifier = Modifier.height(16.dp))

            EquipmentSlots()
        }
    }
}

@Composable
fun EquipmentSlots() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, Color(0xFFD6BFA1), shape = RoundedCornerShape(4.dp))
            )
        }
    }
}