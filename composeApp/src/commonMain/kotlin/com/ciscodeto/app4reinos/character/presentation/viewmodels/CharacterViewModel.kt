package com.ciscodeto.app4reinos.character.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.app4reinos.character.domain.AttributeType
import com.ciscodeto.app4reinos.character.domain.CharacterUi
import com.ciscodeto.app4reinos.character.presentation.screens.CharacterScreenMode
import com.ciscodeto.sinapsia.application.character.create.CharacterCreationService
import com.ciscodeto.sinapsia.application.character.create.CreateCharacter
import com.ciscodeto.sinapsia.application.character.find.FindCharacter
import com.ciscodeto.sinapsia.application.character.update.UpdateCharacter
import com.ciscodeto.sinapsia.domain.attributes.Attributes
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
class CharacterViewModel(
    private val characterCreationService: CharacterCreationService,
    private val createCharacter: CreateCharacter,
    private val updateCharacter: UpdateCharacter,
    private val findCharacter: FindCharacter,
) : ViewModel() {
    val mode = mutableStateOf(CharacterScreenMode.VIEW)
    val character = mutableStateOf(CharacterUi(id = null))
    private val originalAttributes = mutableStateOf(character.value.allAttributesUi())
    val availablePoints = mutableStateOf(30)

    fun init(characterId: Uuid?) {
        if (characterId == null) {
            mode.value = CharacterScreenMode.CREATE
            character.value = CharacterUi(id = null)
        } else {
            mode.value = CharacterScreenMode.VIEW
            findCharacterById(characterId)
        }
        originalAttributes.value = character.value.allAttributesUi()
        availablePoints.value = characterCreationService
            .getRemainingPoints(character.value.attributes(), character.value.level)
    }

    private fun findCharacterById(id: Uuid) {
        viewModelScope.launch {
            character.value = findCharacter.findById(id)?.let { CharacterUi().fromDto(it) } ?: CharacterUi()
        }
    }

    fun updateName(newName: String) {
        character.value = character.value.copy(name = newName)
    }

    fun updateLevel(newLevel: Int) {
        character.value = character.value.copy(level = newLevel)
        availablePoints.value = characterCreationService
            .getRemainingPoints(character.value.attributes(), newLevel)
    }

    fun updateAttribute(attributeType: AttributeType, newValue: Int ) {
        val updatedValue = character.value.getAttribute(attributeType) + newValue
        val canIncrease = newValue > 0 && newValue <= availablePoints.value
        val canDecrease = newValue < 0 && updatedValue >= originalAttributes.value.first { it.type == attributeType }.value
        if (canIncrease || canDecrease) {
            character.value = character.value.setAttribute(attributeType, updatedValue)
            availablePoints.value -= newValue
        }
    }

    fun increaseAttribute(attributeType: AttributeType) {
        updateAttribute(attributeType, 1)
    }

    fun decreaseAttribute(attributeType: AttributeType) {
        updateAttribute(attributeType, -1)
    }

    fun updateCharacter() {
        val char = character.value
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
        val char = character.value
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
}