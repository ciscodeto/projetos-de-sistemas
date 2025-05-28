package com.ciscodeto.app4reinos.core.components.dropdownMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn

@Composable
fun DropdownOptions(
    modifier: Modifier = Modifier,
    items: List<DropdownMenuInfo>,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = !expanded }) {
        Icon(Icons.Default.MoreVert, contentDescription = "More options")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        containerColor = Color.Transparent,
        modifier = modifier,
    ) {
        RoundedColumn()
        {
            DropdownMenuItem(
                text = { Text("Settings") },
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                onClick = {  }
            )
            if (items.isNotEmpty())
                HorizontalDivider()
            for (item in items) {
                DropdownMenuItem(
                    text = { Text(item.text, color = Color(0xFFD6BFA1)) },
                    leadingIcon = item.leadingIcon,
                    trailingIcon = item.trailingIcon,
                    onClick = { item.onClick()
                        expanded = false }
                )
            }
        }
    }
}