package com.ciscodeto.app4reinos.scene.presentation.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.character.presentation.components.StatBar
import com.ciscodeto.app4reinos.scene.domain.CharacterUi

@Composable
fun CharacterCard(
    character: CharacterUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val cardShape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = cardShape,
                clip = false
            )
            .fillMaxHeight()
            .aspectRatio(.75f)
            .clip(cardShape)
            .background(Color(0xff2b1f1a))
            .border(1.dp, Color(0xFF473229), cardShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            val cardWidth = maxWidth
            val cardHeight = maxHeight


            val smallPadding: Dp = cardWidth * 0.05f
            val mediumPadding: Dp = cardWidth * 0.075f
            val largePadding: Dp = cardWidth * 0.04f

            val characterNameFontSize: TextUnit = (cardWidth.value * 0.1f).sp
            val levelFontSize: TextUnit = (cardWidth.value * 0.125f).sp
            val statLabelFontSize: TextUnit = (cardWidth.value * 0.07f).sp
            val statBarHeight: Dp = cardWidth * 0.04f

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.7f)
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .background(Color.DarkGray)
                    )

                    Text(
                        text = character.name.uppercase(),
                        fontSize = characterNameFontSize,
                        color = Color(0xFFD6BFA1),
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    StatBar(
                        total = character.maxHealth,
                        current = character.currentHealth,
                        barHeight = statBarHeight,
                    )

                    StatBar(
                        total = character.maxEnergy,
                        current = character.currentEnergy,
                        barHeight = statBarHeight,
                        foregroundColor = Color(0xFF22869A),
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .shadow(
                                    elevation = 4.dp,
                                    clip = false
                                )
                                .background(
                                    color = Color(0xFF2B1F1A),
                                    shape = RoundedCornerShape(cardWidth * 0.02f)
                                )
                                .border(
                                    border = BorderStroke(1.dp, Color(0xFF473229)),
                                    shape = RoundedCornerShape(cardWidth * 0.02f)
                                )
                                .padding(smallPadding / 2),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = character.level.toString(),
                                color = Color(0xFFD6BFA1),
                                fontSize = levelFontSize,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        val statsList = character.stats.entries.toList()
                        val firstColumnCount = (statsList.size + 1) / 2

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = smallPadding),
                            verticalArrangement = Arrangement.spacedBy(smallPadding / 2)
                        ) {
                            statsList.take(firstColumnCount).forEach { (name, value) ->
                                StatText(
                                    name = name,
                                    value = value,
                                    fontSize = statLabelFontSize
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = smallPadding),
                            verticalArrangement = Arrangement.spacedBy(smallPadding / 2)
                        ) {
                            statsList.drop(firstColumnCount).forEach { (name, value) ->
                                StatText(
                                    name = name,
                                    value = value,
                                    fontSize = statLabelFontSize
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun StatText(name: String, value: Int, fontSize: TextUnit) {
    BoxWithConstraints {
        Text(
            text = "$name $value",
            fontSize = fontSize,
            color = Color(0xFFD6BFA1),
            lineHeight = fontSize * 1.1f,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}