package com.ciscodeto.app4reinos.character.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

class CharactersListViewModel(
    private val findAllCharacters: FindAllCharacters
) : ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    init {
        loadCharacters()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun loadCharacters() {
        viewModelScope.launch {
            _characters.value = findAllCharacters.findAll().map {
                Character(
                    id = it.id.toString(),
                    name = it.name,
                    level = it.level
                )
            }
        }
    }
}