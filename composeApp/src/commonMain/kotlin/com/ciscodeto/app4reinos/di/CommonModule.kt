package com.ciscodeto.app4reinos.di

import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharactersListViewModel
import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharacterViewModel
import com.ciscodeto.sinapsia.application.character.create.CreateCharacter
import com.ciscodeto.sinapsia.application.character.create.CreateCharacterImpl
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import com.ciscodeto.sinapsia.application.character.find.FindAllCharactersImpl
import com.ciscodeto.sinapsia.application.character.find.FindCharacter
import com.ciscodeto.sinapsia.application.character.find.FindCharacterImpl
import com.ciscodeto.sinapsia.di.sinapsiaModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val servicesModule = module {
    single <FindAllCharacters> {
        FindAllCharactersImpl(get())
    }
    single <CreateCharacter> {
        CreateCharacterImpl(get())
    }
    single <FindCharacter> {
        FindCharacterImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        CharactersListViewModel(get())
    }
    viewModel {
        CharacterViewModel(get())
    }
}

fun appModules() = listOf(sinapsiaModule, servicesModule, viewModelModule)