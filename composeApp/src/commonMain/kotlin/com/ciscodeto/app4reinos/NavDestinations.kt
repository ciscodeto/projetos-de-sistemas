package com.ciscodeto.app4reinos

import kotlinx.serialization.Serializable

sealed interface NavDestinations {
    @Serializable
    data object HomeScreen : NavDestinations

    @Serializable
    data class CharacterScreen(val characterId: String? = null) : NavDestinations

    @Serializable
    data object CharactersListScreen : NavDestinations

    @Serializable
    data object ItemScreen : NavDestinations

    @Serializable
    data object ItemsListScreen : NavDestinations

    @Serializable
    data class SceneScreen(val sceneId: String? = null) : NavDestinations

    @Serializable
    data object SceneListScreen : NavDestinations

    @Serializable
    data object SettingsScreen : NavDestinations
}