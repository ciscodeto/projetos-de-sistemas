package com.ciscodeto.app4reinos.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.core.components.inputFields.NumberInputField

@Composable
fun AttributeRow(
    name: String,
    value: Int,
    currentValue: Int? = null,
    modifier: Modifier = Modifier,
    editable: Boolean = false,
    icon: ImageVector,
    onValueChange: (Int) -> Unit = {},
    onCurrentValueChange: (Int) -> Unit = {},
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {}
) {
    var inputValue by remember { mutableStateOf(value.toString()) }
    var inputCurrentValue by remember { mutableStateOf(currentValue.toString()) }

    var hasValueFocus by remember { mutableStateOf(false) }
    var hasCurrentValueFocus by remember { mutableStateOf(false) }

    LaunchedEffect(value) {
        inputValue = value.toString()
    }
    LaunchedEffect(currentValue, hasCurrentValueFocus) {
        inputCurrentValue = currentValue?.toString().orEmpty()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(icon, contentDescription = "Attribute $name icon", tint = Color(0xFFD6BFA1))
            Text(name, color = Color(0xFFD6BFA1), fontSize = 16.sp)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (currentValue != null) {
                NumberInputField(
                    value = inputCurrentValue,
                    onValueChange = { inputCurrentValue = it },
                    modifier = modifier
                        .onFocusChanged { focusState ->
                            if (hasCurrentValueFocus && !focusState.isFocused) {
                                val parsed = inputCurrentValue.toIntOrNull()
                                if (parsed != null)
                                    onCurrentValueChange(parsed)
                                inputCurrentValue = currentValue.toString()
                            }
                            hasCurrentValueFocus = focusState.isFocused
                        },
                )
            }
            AttributeCounter(value = inputValue,
                onValueChange = { inputValue = it },
                onIncrease = onIncrease,
                onDecrease = onDecrease,
                modifier = modifier
                    .onFocusChanged { focusState ->
                        if (hasValueFocus && !focusState.isFocused) {
                            val parsed = inputValue.toIntOrNull()
                            if (parsed != null)
                                onValueChange(parsed - value)
                            inputValue = value.toString()
                        }
                        hasValueFocus = focusState.isFocused
                    }
            )
        }
    }
}

@Composable
fun AttributeCounter(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.background(Color(0xFF1E120C))
    ) {
        StyledIconButton(
            onClick = onDecrease,
            icon = { Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Subtract",
                tint = MaterialTheme.colorScheme.primary
            )}
        )
        NumberInputField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
        )
        StyledIconButton(
            onClick = onIncrease,
            icon = { Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.primary
            )}
        )
    }
}

@Composable
fun StyledIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF2D221B)) // marrom escuro
            .border(
                width = 1.dp,
                color = Color(0xFFD6C7AA), // bege claro
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}