package com.ciscodeto.app4reinos.character.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ciscodeto.app4reinos.character.domain.CharacterUi
import com.ciscodeto.sinapsia.application.character.create.CreateCharacter

class CharacterViewModel(
    private val createCharacter: CreateCharacter,
) : ViewModel() {
    val character = mutableStateOf(CharacterUi())
}