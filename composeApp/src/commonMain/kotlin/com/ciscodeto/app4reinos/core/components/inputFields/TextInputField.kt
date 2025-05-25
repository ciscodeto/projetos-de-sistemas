package com.ciscodeto.app4reinos.core.components.inputFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xFF1E120C), shape = RoundedCornerShape(8.dp)) // Fundo escuro e bordas arredondadas
            .border(1.dp, Color(0xFFD6BFA1), RoundedCornerShape(8.dp)), // Borda marrom claro
        contentAlignment = Alignment.CenterStart // Alinha o texto ao início
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy( // Usar um estilo de texto do Material 3
                color = Color(0xFFD6BFA1), // Cor do texto
                fontSize = 16.sp // Tamanho de fonte "normal"
            ),
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxSize()
        )
    }
}