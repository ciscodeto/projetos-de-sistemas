package com.ciscodeto.app4reinos.di

import com.ciscodeto.app4reinos.character.presentation.CharactersListViewModel
import com.ciscodeto.app4reinos.character.presentation.CreateCharacterViewModel
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import com.ciscodeto.sinapsia.application.character.find.FindAllCharactersImpl
import com.ciscodeto.sinapsia.di.sinapsiaModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val servicesModule = module {
    single <FindAllCharacters> {
        FindAllCharactersImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        CharactersListViewModel(get())
    }
    viewModel {
        CreateCharacterViewModel()
    }
}

fun appModules() = listOf(sinapsiaModule, servicesModule, viewModelModule)