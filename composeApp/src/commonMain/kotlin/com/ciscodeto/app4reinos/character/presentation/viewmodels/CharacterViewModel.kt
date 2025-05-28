package com.ciscodeto.app4reinos.character.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.app4reinos.character.domain.AttributeType
import com.ciscodeto.app4reinos.character.domain.CharacterUi
import com.ciscodeto.app4reinos.character.presentation.screens.CharacterScreenMode.*
import com.ciscodeto.app4reinos.core.ui.events.UiEvent
import com.ciscodeto.sinapsia.application.character.create.CharacterCreationService
import com.ciscodeto.sinapsia.application.character.create.CreateCharacter
import com.ciscodeto.sinapsia.application.character.delete.DeleteCharacter
import com.ciscodeto.sinapsia.application.character.find.FindCharacter
import com.ciscodeto.sinapsia.application.character.update.UpdateCharacter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
class CharacterViewModel(
    private val characterCreationService: CharacterCreationService,
    private val createCharacter: CreateCharacter,
    private val updateCharacter: UpdateCharacter,
    private val deleteCharacter: DeleteCharacter,
    private val findCharacter: FindCharacter,
) : ViewModel() {
    var mode by mutableStateOf(VIEW)
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var character by mutableStateOf(CharacterUi(id = null))
    private var originalAttributes by mutableStateOf(character.attributes)

    var availablePoints by mutableStateOf(30)
    var healthPerPoint = 10
    var energyPerPoint = 10

    fun init(characterId: Uuid?) {
        if (characterId == null) {
            mode = CREATE
            character = CharacterUi(id = null)
            setupFrom(character)
        } else {
            mode = VIEW
            findCharacterById(characterId)
        }
    }

    private fun findCharacterById(id: Uuid) {
        viewModelScope.launch {
            character = findCharacter.findById(id)?.let { CharacterUi().fromDto(it) } ?: CharacterUi()
            setupFrom(character)
        }
    }

    private fun setupFrom(char: CharacterUi) {
        originalAttributes = char.attributes
        availablePoints = characterCreationService.getRemainingPoints(char.attributes(), char.level)
        healthPerPoint = characterCreationService.getEnergyPerPoint()
        energyPerPoint = characterCreationService.getHealthPerPoint()
    }


    fun updateName(newName: String) {
        character = character.copy(name = newName)
    }

    fun updateLevel(newLevel: Int) {
        if (newLevel < 0) return
        if (newLevel < character.level)
            character = character.copy(attributes = originalAttributes)
        availablePoints = characterCreationService
            .getRemainingPoints(character.attributes(), newLevel)
    }

    fun updateAttribute(attributeType: AttributeType, newValue: Int ) {
        val updatedValue = character.getAttribute(attributeType) + newValue
        val canIncrease = newValue in 1..availablePoints
        val canDecrease = newValue < 0 && updatedValue >= originalAttributes.getAttribute(attributeType)
        if (canIncrease || canDecrease) {
            character = character.setAttribute(attributeType, updatedValue)
            availablePoints -= newValue
        }
    }

    fun increaseAttribute(attributeType: AttributeType) {
        updateAttribute(attributeType, 1)
    }

    fun decreaseAttribute(attributeType: AttributeType) {
        updateAttribute(attributeType, -1)
    }

    fun updateCharacter() {
        val char = character
        viewModelScope.launch {
            updateCharacter.update(
                UpdateCharacter.RequestModel(
                    name = char.name,
                    age = 1,
                    level = char.level,
                    experience = 1,
                    gold = 100,
                    attributes = char.attributes(),
                    description = "Null",
                    id = char.id!!,
                    currentHealth = char.currentHealth,
                    currentEnergy = char.currentEnergy,
                )
            )
        }
    }

    fun createCharacter() {
        val char = character
        viewModelScope.launch {
            createCharacter.create(
                CreateCharacter.RequestModel(
                    name = char.name,
                    age = 1,
                    level = char.level,
                    experience = 1,
                    gold = 100,
                    currentEnergy = char.currentEnergy,
                    currentHealth = char.currentHealth,
                    attributes = char.attributes(),
                    description = "Null",
                )
            )
        }
    }

    fun deleteCharacter() {
        val char = character
        viewModelScope.launch {
            deleteCharacter.delete(char.id!!)
            _uiEvent.emit(UiEvent.DeleteConfirmed)
        }
    }

    fun onDeleteClicked() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.ShowDeleteConfirmation)
        }
    }


    fun updateCurrentHealth(amount: Int) {
        var newHealth = amount
        val health = character.attributes.vitality
        
        if (newHealth < 0) return
        if (newHealth > energyPerPoint * health)
            newHealth = energyPerPoint * health
        
        character = character.copy(currentHealth = newHealth)
    }

    fun updateCurrentEnergy(amount: Int) {
        var newEnergy = amount
        val energy = character.attributes.energy
        
        if (newEnergy < 0) return
        if (newEnergy > healthPerPoint * energy) {
            newEnergy = healthPerPoint * energy
        }
        
        character = character.copy(currentEnergy = newEnergy)
    }

    fun switchMode() {
        mode = when (mode) {
            VIEW -> EDIT
            EDIT -> VIEW
            CREATE -> VIEW
        }
    }
}