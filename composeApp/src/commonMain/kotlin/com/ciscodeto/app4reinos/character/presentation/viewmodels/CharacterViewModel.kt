package com.ciscodeto.app4reinos.character.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class CharacterViewModel(
    private val characterCreationService: CharacterCreationService,
    private val createCharacter: CreateCharacter,
    private val updateCharacter: UpdateCharacter,
    private val findCharacter: FindCharacter,
) : ViewModel() {
    val mode = mutableStateOf(CharacterScreenMode.VIEW)
    @OptIn(kotlin.uuid.ExperimentalUuidApi::class)
    val character = mutableStateOf(CharacterUi(id = null))
    val availablePoints = mutableStateOf(0)

    @OptIn(ExperimentalUuidApi::class)
    fun init(characterId: Uuid?) {
        if (characterId == null) {
            mode.value = CharacterScreenMode.CREATE
            character.value = CharacterUi(id = null)
        } else {
            mode.value = CharacterScreenMode.VIEW
            findCharacterById(characterId)
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun findCharacterById(id: Uuid) {
        viewModelScope.launch {
            character.value = findCharacter.findById(id)?.let { CharacterUi().fromDto(it) } ?: CharacterUi()
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun updateName(newName: String) {
        character.value = character.value.copy(name = newName)
    }

    @OptIn(ExperimentalUuidApi::class)
    fun updateLevel(newLevel: Int) {
        character.value = character.value.copy(level = newLevel)
    }

    fun increaseAttribute(attribute: String) {
        val char = character.value
        if (characterCreationService.getRemainingPoints(char.attributes(), char.level) > 0) {
            val updatedValue = char.getAttribute(attribute) + 1
            character.value = char.setAttribute(attribute, updatedValue)
        }
    }

    fun decreaseAttribute(attribute: String) {
        val char = character.value
        val currentValue = char.getAttribute(attribute)
        if (currentValue > 0) {
            val updatedValue = currentValue - 1
            character.value = char.setAttribute(attribute, updatedValue)
        }
    }

    @OptIn(ExperimentalUuidApi::class)
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
                    attributes = Attributes(
                        vitality = char.vitality,
                        energy = char.energy,
                        strength = char.strength,
                        endurance = char.endurance,
                        dexterity = char.dexterity,
                        intelligence = char.intelligence,
                        wisdom = char.wisdom,
                        charisma = char.charisma
                    ),
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
                    attributes = Attributes(
                        vitality = char.vitality,
                        energy = char.energy,
                        strength = char.strength,
                        endurance = char.endurance,
                        dexterity = char.dexterity,
                        intelligence = char.intelligence,
                        wisdom = char.wisdom,
                        charisma = char.charisma
                    ),
                    description = "Null",
                )
            )
        }
    }
}