package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.character.presentation.components.StatBar
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn
import com.ciscodeto.app4reinos.scene.domain.CharacterUi

@Composable
fun CharacterCard(
    character: CharacterUi,
    modifier: Modifier,
    onClick: () -> Unit = {},
    onRemove: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .aspectRatio(3f / 4f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xff2b1f1a))
            .border(1.dp, Color(0xFF473229), RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ─── Parte superior ───
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(Color.DarkGray)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = character.name,
                    fontSize = 14.sp,
                    color = Color(0xFFD6BFA1),
                    fontWeight = FontWeight.Thin,
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Row {
                    StatBar(
                        total = 99,
                        current = 99,
                        barHeight = 4.dp
                    )
                }
                Row {
                    StatBar(
                        total = 99,
                        current = 50,
                        barHeight = 4.dp,
                        foregroundColor = Color(0xFF22869A)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Bloco 1: Nível
                    Box (
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .background(
                                color = Color(0xFF2B1F1A),
                                shape = RoundedCornerShape(4.dp),
                            )
                            .border(
                                border = BorderStroke(1.dp, Color(0xFF473229)),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp)
                            ,
                        contentAlignment = Alignment.Center
                        ) {
                        Text(
                            modifier = Modifier
                                .padding(0.dp),
                            text = character.level.toString(),
                            color = Color(0xFFD6BFA1),
                            fontSize = 12.sp,
                            lineHeight = 10.sp,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        character.stats.entries.take(3).forEach { (name, value) ->
                            Text(
                                text = "$name  $value",
                                fontSize = 8.sp,
                                color = Color(0xFFD6BFA1),
                                lineHeight = 8.sp,
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        character.stats.entries.drop(3).forEach { (name, value) ->
                            Text(
                                text = "$name  $value",
                                fontSize = 8.sp,
                                color = Color(0xFFD6BFA1),
                                lineHeight = 8.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}