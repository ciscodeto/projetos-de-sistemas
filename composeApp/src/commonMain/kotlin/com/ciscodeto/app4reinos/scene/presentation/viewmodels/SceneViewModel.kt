package com.ciscodeto.app4reinos.scene.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ciscodeto.app4reinos.scene.domain.ActionUi
import com.ciscodeto.app4reinos.scene.domain.CharacterUi
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class SceneViewModel(

) : ViewModel() {
    var actor: CharacterUi? by mutableStateOf(null)
    val action: ActionUi? by mutableStateOf(null)
    val target: CharacterUi? by mutableStateOf(null)
    val reaction: ActionUi? by mutableStateOf(null)

    fun selectActor() {
        actor = if (actor == null)
            CharacterUi(id = null, name = "Bartran", stats = mapOf(
                "FOR" to 99,
                "RES" to 99,
                "AGI" to 99,
                "INT" to 99,
                "SAB" to 99,
                "CAR" to 99
            ), level = 99, health = 99, energy = 99)
        else
            null
    }

    fun rollActorDice() {
        TODO("Not yet implemented")
    }

    fun selectAction() {
        TODO("Not yet implemented")
    }
}