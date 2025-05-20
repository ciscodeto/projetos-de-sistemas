package com.ciscodeto.app4reinos

import kotlinx.serialization.Serializable

sealed interface NavDestinations {
    @Serializable
    data object HomeScreen : NavDestinations

    @Serializable
    data class CharacterScreen(val characterId: String? = null) : NavDestinations

    @Serializable
    data class EditCharacterScreen(val characterId: String) : NavDestinations

    @Serializable
    data class CharacterDetailScreen(val characterId: String) : NavDestinations

    @Serializable
    data object CharactersListScreen : NavDestinations

    @Serializable
    data class CreateItemScreen(val characterId: String? = null) : NavDestinations

    @Serializable
    data class EditItemScreen(val itemId: String) : NavDestinations

    @Serializable
    data object ItemsListScreen : NavDestinations

    @Serializable
    data class ActionSelectionScreen(val characterId: String) : NavDestinations

    @Serializable
    data class TargetSelectionScreen(val actionId: String, val sourceCharacterId: String) : NavDestinations

    @Serializable
    data class ActionExecutionScreen(val actionId: String, val sourceCharacterId: String, val targetCharacterId: String?) : NavDestinations

    @Serializable
    data class CounterActionScreen(
        val originalActionId: String,
        val responderId: String,
        val initiatorId: String
    ) : NavDestinations

    @Serializable
    data object ActionLogScreen : NavDestinations

    @Serializable
    data object SettingsScreen : NavDestinations
}