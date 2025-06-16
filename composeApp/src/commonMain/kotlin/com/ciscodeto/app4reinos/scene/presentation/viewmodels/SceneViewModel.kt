package com.ciscodeto.app4reinos.scene.presentation.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.app4reinos.scene.domain.ActionUi
import com.ciscodeto.app4reinos.scene.domain.CharacterUi
import com.ciscodeto.app4reinos.scene.presentation.enums.BottomSheetMode
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SceneViewModel(
    private val findAllCharactersUseCase: FindAllCharacters,
    //private val findAllActionsUseCase: FindAllActionsUseCase
) : ViewModel() {
    var actor: CharacterUi? by mutableStateOf(null)
        private set
    var action: ActionUi? by mutableStateOf(null)
        private set
    var target: CharacterUi? by mutableStateOf(null)
        private set
    var reaction: ActionUi? by mutableStateOf(null)
        private set

    var canSelectReaction by mutableStateOf(false)
        private set
    var canRollReaction by mutableStateOf(false)
        private set
    var gameLog by mutableStateOf(listOf<String>())
        private set

    private val _selectableCharacters = MutableStateFlow<List<CharacterUi>>(emptyList())
    val selectableCharacters: StateFlow<List<CharacterUi>> = _selectableCharacters.asStateFlow()

    private val _selectableActions = MutableStateFlow<List<ActionUi>>(emptyList())
    val selectableActions: StateFlow<List<ActionUi>> = _selectableActions.asStateFlow()

    var bottomSheetMode by mutableStateOf(BottomSheetMode.LOG)
        private set

    init {
        addLogEntry("Bem-vindo à Cena!")
    }

    private fun addLogEntry(entry: String) {
        gameLog = gameLog + entry
    }

    fun initiateActorSelection() {
        bottomSheetMode = BottomSheetMode.CHARACTER_SELECTION
        if (_selectableCharacters.value.isEmpty()) {
            loadSelectableCharacters()
        }
    }

    private fun loadSelectableCharacters() {
        viewModelScope.launch {
            findAllCharactersUseCase.findAll().collect { dtoList ->
                _selectableCharacters.value = dtoList.map { dto ->
                    val attr = dto.attributes
                    CharacterUi(
                        id = dto.id,
                        name = dto.name,
                        stats = mapOf(
                                "FOR" to attr.strength,
                                "RES" to attr.endurance,
                                "AGI" to attr.dexterity,
                                "INT" to attr.intelligence,
                                "SAB" to attr.wisdom,
                                "CAR" to attr.charisma),
                        level = dto.level,
                        health = dto.currentHealth,
                        energy = dto.currentEnergy
                    )
                }
            }
        }
    }

    fun confirmActorSelection(selectedCharacter: CharacterUi) {
        if (actor != selectedCharacter) {
            actor = selectedCharacter
            action = null
            target = null
        }
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun removeActor() {
        actor = null
        action = null
        target = null
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun initiateActionSelection() {
        if (actor == null) return
        bottomSheetMode = BottomSheetMode.ACTION_SELECTION
        if (_selectableActions.value.isEmpty()) {
            loadSelectableActions()
        }
    }

    fun initiateReactionSelection() {
        if (actor == null) return
        bottomSheetMode = BottomSheetMode.REACTION_SELECTION
        if (_selectableActions.value.isEmpty()) {
            loadSelectableActions()
        }
    }

    private fun loadSelectableActions() {
        viewModelScope.launch {
            _selectableActions.value = listOf(
                ActionUi(id = Uuid.random(), name = "Ataque Rápido", description = "Um golpe veloz.", cost = 1, requiresTarget = true, icon = Icons.Filled.Bolt),
                ActionUi(id = Uuid.random(), name = "Defender Posição", description = "Aumenta a defesa.", cost = 1, requiresTarget = false, icon = Icons.Filled.Bolt),
                ActionUi(
                    id = Uuid.random(),
                    name = "Bola de Fogo",
                    description = "Lança uma bola de fogo.",
                    cost = 2,
                    requiresTarget = true,
                    icon = Icons.Filled.Bolt
                ),
                ActionUi(id = Uuid.random(), name = "Curar Ferimentos", description = "Restaura um pouco de vida.", cost = 2, requiresTarget = false, icon = Icons.Filled.Bolt)
            )
        }
    }

    fun confirmActionSelection(selectedAction: ActionUi) {
        if (action != selectedAction) {
            action = selectedAction
            target = null
        }
        bottomSheetMode = BottomSheetMode.LOG

        if (!selectedAction.requiresTarget) {

        }
    }


    fun confirmReactionSelection(selectedReaction: ActionUi) {
        if (reaction != selectedReaction) {
            reaction = selectedReaction
        }
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun removeAction() {
        action = null
        target = null
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun removeReaction() {
        reaction = null
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun initiateTargetSelection() {
        if (actor == null || action == null || action?.requiresTarget == false) return
        bottomSheetMode = BottomSheetMode.TARGET_SELECTION
        if (_selectableCharacters.value.isEmpty()) {
            loadSelectableCharacters()
        }
    }

    fun confirmTargetSelection(selectedTarget: CharacterUi) {
        target = selectedTarget
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun removeTarget() {
        target = null
        bottomSheetMode = BottomSheetMode.LOG
    }
}