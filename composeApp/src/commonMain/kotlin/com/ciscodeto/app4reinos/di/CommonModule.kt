package com.ciscodeto.app4reinos.di

import androidx.room.Room
import com.ciscodeto.app4reinos.character.presentation.CharactersListViewModel
import com.ciscodeto.app4reinos.character.presentation.CreateCharacterViewModel
import com.ciscodeto.sinapsia.application.character.find.FindAllCharactersImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//val serviceModule = module {
//    single {
//        FindAllCharactersImpl(get())
//    }
//}

val viewModelModule = module {
    viewModel {
        CharactersListViewModel()
    }
    viewModel {
        CreateCharacterViewModel()
    }
}