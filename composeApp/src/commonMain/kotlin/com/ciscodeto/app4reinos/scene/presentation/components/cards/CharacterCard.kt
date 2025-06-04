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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ciscodeto.app4reinos.character.presentation.components.StatBar
import com.ciscodeto.app4reinos.core.components.containers.RoundedColumn
import com.ciscodeto.app4reinos.scene.domain.CharacterUi

@Composable
fun CharacterCard(
    character: CharacterUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val cardShape = RoundedCornerShape(4.dp) // Definir a forma uma vez
    Box(
        modifier = modifier
            .shadow( // Sombra para o Card
                elevation = 4.dp, // Ajuste a elevação conforme necessário
                shape = cardShape,
                clip = false // Geralmente false para que a sombra não recorte o conteúdo interno se o background não for opaco
            )
            .fillMaxHeight() // Para manter a altura consistente com outros slots na mesma Row
            .aspectRatio(.75f) // Proporção similar ao EmptySlot
            .clip(cardShape) // Use a fixed dp for outer rounding, or scale it too
            .background(Color(0xff2b1f1a))
            .border(1.dp, Color(0xFF473229), cardShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            // Use constraints to make inner elements proportional
            val cardWidth = maxWidth
            val cardHeight = maxHeight // Available if needed, but width is primary driver here

            // Define base sizes relative to cardWidth
            // These factors are chosen by eye and can be tuned
            val smallPadding: Dp = cardWidth * 0.05f
            val mediumPadding: Dp = cardWidth * 0.075f // For overall padding
            val largePadding: Dp = cardWidth * 0.04f

            val characterNameFontSize: TextUnit = (cardWidth.value * 0.1f).sp // Adjusted for better fit
            val levelFontSize: TextUnit = (cardWidth.value * 0.125f).sp // Make level font size larger
            val statLabelFontSize: TextUnit = (cardWidth.value * 0.07f).sp // For "FOR 99"
            val statBarHeight: Dp = cardWidth * 0.04f

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(mediumPadding), // Proportional padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ─── Parte superior (Imagem e Nome) ───
                Column(
                    modifier = Modifier.weight(1f), // Takes roughly half the height
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center // Center portrait and name vertically
                ) {
                    Box( // Placeholder for Character Image
                        modifier = Modifier
                            .fillMaxHeight(0.7f) // Portrait takes 70% of this section's height
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .background(Color.DarkGray) // Placeholder color
                    )
                    // If you have an image Painter:
                    // Image(painter = yourPainter, contentDescription = character.name, modifier = ...)

                    //Spacer(modifier = Modifier.height(smallPadding))

                    Text(
                        text = character.name.uppercase(),
                        fontSize = characterNameFontSize,
                        color = Color(0xFFD6BFA1),
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis // Prevent name overflow if too long
                    )
                }

                // ─── Parte inferior (Barras de Stats e Atributos) ───
                Column(
                    modifier = Modifier.weight(1f), // Takes roughly half the height
                    verticalArrangement = Arrangement.SpaceEvenly // Distribute space
                ) {
                    // Health Bar
                    StatBar(
                        total = character.health,
                        current = character.health,
                        barHeight = statBarHeight,
                        // Add foregroundColor and backgroundColor if your StatBar supports them
                        // foregroundColor = Color.Red,F
                        // backgroundColor = Color(0xFF532323)
                    )

                    //Spacer(modifier = Modifier.height(smallPadding / 2)) // Smaller spacer

                    // Mana/Resource Bar
                    StatBar(
                        total = character.energy,
                        current = character.energy,
                        barHeight = statBarHeight,
                        foregroundColor = Color(0xFF22869A),
                        // backgroundColor = Color(0xFF21454F)
                    )

                    //Spacer(modifier = Modifier.height(mediumPadding)) // A bit more space before stats grid

                    // Stats Row (Level + Attributes)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically // Key fix for vertical alignment!
                    ) {
                        // Bloco 1: Nível
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()// Give slightly more visual weight to level box
                                .aspectRatio(1f)
                                .shadow( // Sombra para o Card
                                    elevation = 4.dp, // Ajuste a elevação conforme necessário
                                    clip = false // Geralmente false para que a sombra não recorte o conteúdo interno se o background não for opaco
                                )// Make it a square
                                .background(
                                    color = Color(0xFF2B1F1A), // Same as card dark background
                                    shape = RoundedCornerShape(cardWidth * 0.02f) // Proportional rounding
                                )
                                .border(
                                    border = BorderStroke(1.dp, Color(0xFF473229)), // Border can be fixed or proportional
                                    shape = RoundedCornerShape(cardWidth * 0.02f)
                                )
                                .padding(smallPadding / 2), // Proportional inner padding
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = character.level.toString(),
                                color = Color(0xFFD6BFA1),
                                fontSize = levelFontSize, // Use the larger, proportional font size
                                fontWeight = FontWeight.Bold, // Make level more prominent
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center) // Ensure text is centered
                            )
                        }

                        //Spacer(modifier = Modifier.fillMaxWidth(0.05f)) // Spacer between level and stats

                        // Stats Columns
                        // Calculate how many stats per column
                        val statsList = character.stats.entries.toList()
                        val firstColumnCount = (statsList.size + 1) / 2 // Handles odd numbers, puts more in first column

                        // Column for first half of stats
                        Column(
                            modifier = Modifier
                                .weight(1f) // Give more space to stats columns
                                .padding(start = smallPadding),
                            verticalArrangement = Arrangement.spacedBy(smallPadding / 2) // Proportional spacing
                        ) {
                            statsList.take(firstColumnCount).forEach { (name, value) ->
                                StatText(
                                    name = name,
                                    value = value,
                                    fontSize = statLabelFontSize
                                )
                            }
                        }

                        // Column for second half of stats
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
    // Using BoxWithConstraints for each stat text to prevent overflow if individual stat name is too long
    // This is a bit more complex but robust for preventing wrap issues for individual stat lines.
    // A simpler alternative if you fix stat abbreviation lengths (e.g., 3 chars) is to just use Text.
    BoxWithConstraints {
        Text(
            text = "$name $value", // Removed extra space for compactness
            fontSize = fontSize,
            color = Color(0xFFD6BFA1),
            lineHeight = fontSize * 1.1f, // Slightly more line height for readability
            maxLines = 1, // Ensure it doesn't wrap
            overflow = TextOverflow.Visible, // Or Ellipsis if you prefer for extreme cases
            // Note: With proper sizing, Visible should be fine.
            modifier = Modifier.fillMaxWidth() // Allow text to use available width
        )
    }
}