package com.ciscodeto.app4reinos.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import managerapp4reinos.composeapp.generated.resources.Res
import managerapp4reinos.composeapp.generated.resources.oswald_bold
import managerapp4reinos.composeapp.generated.resources.oswald_light
import managerapp4reinos.composeapp.generated.resources.oswald_medium
import managerapp4reinos.composeapp.generated.resources.oswald_regular
import managerapp4reinos.composeapp.generated.resources.oswald_semiBold
import managerapp4reinos.composeapp.generated.resources.playfair_display_bold
import managerapp4reinos.composeapp.generated.resources.playfair_display_extra_bold
import managerapp4reinos.composeapp.generated.resources.playfair_display_medium
import managerapp4reinos.composeapp.generated.resources.playfair_display_regular
import managerapp4reinos.composeapp.generated.resources.playfair_display_semi_bold
import org.jetbrains.compose.resources.Font

@Composable
fun BodyFontFamily() = FontFamily(
        Font(Res.font.oswald_light, weight = FontWeight.Light),
        Font(Res.font.oswald_regular, weight = FontWeight.Normal),
        Font(Res.font.oswald_medium, weight = FontWeight.Medium),
        Font(Res.font.oswald_semiBold, weight = FontWeight.SemiBold),
        Font(Res.font.oswald_bold, weight = FontWeight.Bold)
    )

@Composable
fun DisplayFontFamily() = FontFamily(
        Font(Res.font.playfair_display_regular, weight = FontWeight.Light),
        Font(Res.font.playfair_display_medium, weight = FontWeight.Normal),
        Font(Res.font.playfair_display_semi_bold, weight = FontWeight.Medium),
        Font(Res.font.playfair_display_bold, weight = FontWeight.SemiBold),
        Font(Res.font.playfair_display_extra_bold, weight = FontWeight.Bold)
    )



@Composable
fun ReinosTypography() = Typography().run {
    val displayFontFamily = DisplayFontFamily()
    val bodyFontFamily = BodyFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = labelSmall.copy(fontFamily = bodyFontFamily),
    )
}

