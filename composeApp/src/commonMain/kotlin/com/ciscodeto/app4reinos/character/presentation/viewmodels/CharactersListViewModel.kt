package com.ciscodeto.app4reinos.character.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.app4reinos.character.domain.CharacterUi
import com.ciscodeto.app4reinos.character.presentation.components.ElementUi
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class CharactersListViewModel(
    private val findAllCharacters: FindAllCharacters
) : ViewModel() {
    private val _characters = MutableStateFlow<List<ElementUi>>(emptyList())
    val characters: StateFlow<List<ElementUi>> = _characters

    init {
        loadCharacters()
    }

    @OptIn(ExperimentalUuidApi::class)
    fun loadCharacters() {
        viewModelScope.launch {
            findAllCharacters.findAll().collect { dtoList ->
                _characters.value = dtoList.map { ElementUi(it.name, it.level, it.id) }
            }
        }
    }
}