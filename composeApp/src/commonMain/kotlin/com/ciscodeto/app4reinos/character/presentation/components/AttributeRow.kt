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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.core.components.inputFields.NumberInputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeRow(
    name: String,
    value: Int,
    currentValue: Int? = null,
    modifier: Modifier = Modifier,
    editable: Boolean = false,
    icon: ImageVector,
    onValueChange: (String) -> Unit = {},
    onCurrentValueChange: (String) -> Unit = {},
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {}
) {
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
            if (true) {
                NumberInputField(
                    currentValue.toString(),
                    onValueChange = onCurrentValueChange
                )
            }
            AttributeCounter(value = value,
                onValueChange = onValueChange,
                onIncrease = onIncrease,
                onDecrease = onDecrease
            )
        }
    }
}

@Composable
fun AttributeCounter(
    value: Int,
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
//        Text(
//            text = value.toString(),
//            color = Color(0xFFD6BFA1),
//            modifier = Modifier
//                .padding(horizontal = 8.dp)
//                .size(24.dp),
//            textAlign = TextAlign.Center,
//        )
        NumberInputField(value = value.toString(), onValueChange = onValueChange)
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