package com.ciscodeto.managerapp4reinos.di

import com.ciscodeto.managerapp4reinos.character.presentation.CharactersListViewModel
import com.ciscodeto.managerapp4reinos.character.presentation.CreateCharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    viewModel {
        CharactersListViewModel()
    }
    viewModel {
        CreateCharacterViewModel()
    }
}