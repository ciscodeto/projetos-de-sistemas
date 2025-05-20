package com.ciscodeto.app4reinos.character.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class CharactersListViewModel(
    private val findAllCharacters: FindAllCharacters
) : ViewModel() {
    private val _characters = MutableStateFlow<List<Element>>(emptyList())
    val characters: StateFlow<List<Element>> = _characters

    init {
        loadCharacters()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun loadCharacters() {
        viewModelScope.launch {
            _characters.value = findAllCharacters.findAll().map {
                Element(
                    id = it.id.value(),
                    name = it.name,
                    level = it.level
                )
            }
        }
    }
}

data class Element @OptIn(ExperimentalUuidApi::class) constructor(
    val name: String,
    val level: Int,
    val id: Uuid,
)