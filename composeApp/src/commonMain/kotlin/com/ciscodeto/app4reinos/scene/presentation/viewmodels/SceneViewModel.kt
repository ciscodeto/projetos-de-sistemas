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
import com.ciscodeto.app4reinos.scene.domain.LogEntry
import com.ciscodeto.app4reinos.scene.presentation.enums.BottomSheetMode
import com.ciscodeto.app4reinos.scene.presentation.enums.LogEntryType
import com.ciscodeto.sinapsia.application.action.find.FindAllActions
import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import com.ciscodeto.sinapsia.application.dice.ClashService
import com.ciscodeto.sinapsia.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.actions.implementations.seedBaseActionsIfEmpty
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class SceneViewModel(
    private val findAllCharactersUseCase: FindAllCharacters,
    private val findAllActionsUseCase: FindAllActions,
    private val clashService: ClashService,
    private val repository: ActionRepository,
) : ViewModel() {
    var actor: CharacterUi? by mutableStateOf(null)
        private set

    var action: ActionUi? by mutableStateOf(null)
        private set
    private var actionResult: ActionResult? by mutableStateOf(null)

    var target: CharacterUi? by mutableStateOf(null)
        private set
    var reaction: ActionUi? by mutableStateOf(null)
        private set

    private var clashResult: List<String>? by mutableStateOf(null)

    var canSelectReaction by mutableStateOf(false)
        private set
    var canRollReaction by mutableStateOf(false)
        private set

    private val _gameLog = MutableStateFlow<List<LogEntry>>(emptyList())
    val gameLog: StateFlow<List<LogEntry>> = _gameLog.asStateFlow()

    private val _selectableCharacters = MutableStateFlow<List<CharacterUi>>(emptyList())
    val selectableCharacters: StateFlow<List<CharacterUi>> = _selectableCharacters.asStateFlow()

    private val _selectableActions = MutableStateFlow<List<ActionUi>>(emptyList())
    val selectableActions: StateFlow<List<ActionUi>> = _selectableActions.asStateFlow()

    var bottomSheetMode by mutableStateOf(BottomSheetMode.LOG)
        private set

    private var healthPerPoint = 10
    private var energyPerPoint = 10

    init {
        addLogEntry("Bem-vindo à Cena!")
    }

    private fun addLogEntry(text: String, type: LogEntryType = LogEntryType.INFO) {
        _gameLog.value += LogEntry(text = text, type = type)
    }

    private fun addLogEntries(entries: List<Pair<String, LogEntryType>>) {
        val newEntries = entries.map { LogEntry(text = it.first, type = it.second) }
        _gameLog.value += newEntries
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
                            "CAR" to attr.charisma
                        ),
                        level = dto.level,
                        currentHealth = dto.currentHealth,
                        currentEnergy = dto.currentEnergy,
                        maxHealth = attr.vitality * healthPerPoint,
                        maxEnergy = attr.energy * energyPerPoint,
                    )
                }
            }
        }
    }

    fun confirmActorSelection(selectedCharacter: CharacterUi) {
        if (actor != selectedCharacter) {
            resetTurnState(keepActor = false)
            actor = selectedCharacter
        }
        bottomSheetMode = BottomSheetMode.LOG
    }

    fun removeActor() {
        resetTurnState(keepActor = false)
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
            seedBaseActionsIfEmpty(repository)

            findAllActionsUseCase.findAll().collect { dtoList ->
                _selectableActions.value = dtoList.map { it ->
                    ActionUi(
                        id = it.id,
                        name = it.name,
                        description = it.name,
                        cost = it.energyCost,
                        requiresTarget = it.requiresTarget,
                        icon = Icons.Filled.Bolt
                    )
                }
            }
        }
    }

    fun confirmActionSelection(selectedAction: ActionUi) {
        if (action != selectedAction) {
            action = selectedAction
            target = null
        }
        bottomSheetMode = BottomSheetMode.LOG

        if (!selectedAction.requiresTarget) {
            bottomSheetMode = BottomSheetMode.LOG
            return
        } else {
            initiateTargetSelection()
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

    fun rollAction() {
        if (actor == null || action == null || target == null) return
        viewModelScope.launch {
            actor!!.id?.let {
                actionResult = clashService.executeActorAction(action!!.id, it, target!!.id)
            }
            if (actionResult != null) {
                addLogEntry(
                    actionResult!!.message,
                    if (actionResult!!.success) LogEntryType.ACTION else LogEntryType.FAILURE
                )
                if (actionResult!!.success && action!!.requiresTarget) {
                    canSelectReaction = true
                }
            }
        }
    }

    fun rollReaction() {
        if (actor == null || reaction == null || target == null) return
        viewModelScope.launch {
            actor!!.id?.let {
                clashResult = clashService.executeReaction(reaction!!.id)
            }
            if (clashResult != null) {
                clashResult?.first()?.let { addLogEntry(it, LogEntryType.REACTION) }
                clashResult?.filter { it != clashResult!!.first() }
                    ?.map { addLogEntry(it, LogEntryType.SUCCESS) }
            }

            refreshActor()
            refreshTarget()
            delay(1000)
            resetTurnState(keepActor = true)
        }
    }

    private fun refreshTarget() {
        target?.id?.let { targetId ->
            viewModelScope.launch {
                val dto = findAllCharactersUseCase.findAll().firstOrNull()?.find { it.id == targetId } ?: return@launch
                val newTarget = target?.copy(
                    currentHealth = dto.currentHealth,
                    currentEnergy = dto.currentEnergy
                )

                target = newTarget
            }
        }
    }

    private fun refreshActor() {
        actor?.id?.let { actorId ->
            viewModelScope.launch {
                val dto = findAllCharactersUseCase.findAll().firstOrNull()?.find { it.id == actorId }
                val attr = dto?.attributes ?: return@launch

                val newActor = actor?.copy(
                    currentHealth = dto.currentHealth,
                    currentEnergy = dto.currentEnergy
                )

                actor = newActor
            }
        }
    }

    private fun resetTurnState(keepActor: Boolean) {
        if (!keepActor) {
            actor = null
        }
        action = null
        target = null
        reaction = null
        canSelectReaction = false
        actionResult = null
        clashResult = null
    }
}