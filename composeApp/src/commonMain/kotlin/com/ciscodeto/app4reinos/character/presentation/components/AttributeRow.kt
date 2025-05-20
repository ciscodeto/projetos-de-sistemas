package com.ciscodeto.app4reinos.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeRow(
    name: String,
    value: Int,
    modifier: Modifier = Modifier,
    editable: Boolean = false,
    icon: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, color = Color(0xFFD6BFA1), fontSize = 16.sp)
        AttributeCounter(value = value)
    }
}

@Composable
fun AttributeCounter(
    value: Int,
    modifier: Modifier = Modifier,
    editable: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.background(Color(0xFF1E120C))
    ) {
        StyledIconButton(
            onClick = {},
            icon = { Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Subtract",
                tint = MaterialTheme.colorScheme.primary
            )}
        )
        Text(
            text = value.toString(),
            color = Color(0xFFD6BFA1),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        StyledIconButton(
            onClick = {},
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