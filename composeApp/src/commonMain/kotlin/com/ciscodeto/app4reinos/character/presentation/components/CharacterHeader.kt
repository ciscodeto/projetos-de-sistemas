package com.ciscodeto.app4reinos.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn
import com.ciscodeto.app4reinos.core.components.inputFields.NumberInputField
import com.ciscodeto.app4reinos.core.components.inputFields.TextInputField

@Composable
fun CharacterHeaderView(
    name: String,
    level: Int
) {
    CharacterHeaderBase(
        name = { modifier ->
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = modifier
            )
        },
        level = {
            Text(
                text = "$level",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        },
    )
}

@Composable
fun EditableCharacterHeader(
    name: String,
    level: Int,
    onNameChange: (String) -> Unit,
    onLevelChange: (Int) -> Unit,
) {
    var inputLevel by remember { mutableStateOf(level.toString()) }
    var hasFocus by remember { mutableStateOf(true) }

    LaunchedEffect(level) {
        if (level.toString() != inputLevel) {
            inputLevel = level.toString()
        }
    }

    CharacterHeaderBase(
        name = { modifier ->
            TextInputField(
                value = name,
                onValueChange = { value -> onNameChange(value) },
                modifier = modifier
            )
        },
        level = { modifier ->
            NumberInputField(
                value = inputLevel,
                onValueChange = { value ->
                    inputLevel = value
                },
                modifier = modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (hasFocus && !focusState.isFocused) {
                            val parsed = inputLevel.toIntOrNull()
                            if (parsed != null) {
                                onLevelChange(parsed)
                            } else {
                                inputLevel = level.toString()
                            }
                        }
                        hasFocus = focusState.isFocused },
            )
        }
    )
}

@Composable
fun CharacterHeaderBase(
    modifier: Modifier = Modifier,
    name: @Composable (modifier: Modifier) -> Unit,
    level: @Composable (modifier: Modifier) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Person,
                "Empty Character Picture",
                tint = Color.Black,
                modifier = Modifier
                    .fillMaxSize(.5f)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        name(Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        RoundedColumn(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "NÍVEL",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            level(Modifier)
        }
    }
}