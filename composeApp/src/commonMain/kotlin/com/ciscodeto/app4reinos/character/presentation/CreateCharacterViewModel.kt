package com.ciscodeto.app4reinos.character.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateCharacterViewModel(

) : ViewModel() {
    private val _name = mutableStateOf("")
    val name: State<String> = _name

}