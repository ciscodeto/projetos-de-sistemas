package com.ciscodeto.app4reinos.di

import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharactersListViewModel
import com.ciscodeto.app4reinos.character.presentation.viewmodels.CharacterViewModel
import com.ciscodeto.app4reinos.scene.presentation.viewmodels.SceneListViewModel
import com.ciscodeto.app4reinos.scene.presentation.viewmodels.SceneViewModel
import com.ciscodeto.sinapsia.application.character.create.CharacterCreationService
import com.ciscodeto.sinapsia.application.character.create.CharacterCreationServiceImpl
import com.ciscodeto.sinapsia.application.character.create.CreateCharacter
import com.ciscodeto.sinapsia.application.character.create.CreateCharacterImpl
import com.ciscodeto.sinapsia.application.character.delete.DeleteCharacter
import com.ciscodeto.sinapsia.application.character.delete.DeleteCharacterImpl
import com.ciscodeto.sinapsia.application.character.find.FindAllCharacters
import com.ciscodeto.sinapsia.application.character.find.FindAllCharactersImpl
import com.ciscodeto.sinapsia.application.character.find.FindCharacter
import com.ciscodeto.sinapsia.application.character.find.FindCharacterImpl
import com.ciscodeto.sinapsia.application.character.update.UpdateCharacter
import com.ciscodeto.sinapsia.application.character.update.UpdateCharacterImpl
import com.ciscodeto.sinapsia.application.item.create.CreateItem
import com.ciscodeto.sinapsia.application.item.create.CreateItemImpl
import com.ciscodeto.sinapsia.application.item.find.FindAllItems
import com.ciscodeto.sinapsia.application.item.find.FindAllItemsImpl
import com.ciscodeto.sinapsia.application.item.find.FindItem
import com.ciscodeto.sinapsia.application.item.find.FindItemImpl
import com.ciscodeto.sinapsia.application.item.update.UpdateItem
import com.ciscodeto.sinapsia.application.item.update.UpdateItemImpl
import com.ciscodeto.sinapsia.di.sinapsiaModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val characterServices = module {
    single <FindAllCharacters> {
        FindAllCharactersImpl(get())
    }
    single <CreateCharacter> {
        CreateCharacterImpl(get())
    }
    single <UpdateCharacter> {
        UpdateCharacterImpl(get())
    }
    single <DeleteCharacter> {
        DeleteCharacterImpl(get())
    }
    single <FindCharacter> {
        FindCharacterImpl(get())
    }
    single <CharacterCreationService> {
        CharacterCreationServiceImpl()
    }
}

val itemServices = module {
    single <FindAllItems> {
        FindAllItemsImpl(get())
    }
    single <CreateItem> {
        CreateItemImpl(get())
    }
    single <UpdateItem> {
        UpdateItemImpl(get())
    }
    single <FindItem> {
        FindItemImpl(get())
    }
}

val sceneServices = module {

}

val viewModelModule = module {
    viewModel {
        CharactersListViewModel(get())
    }
    viewModel {
        CharacterViewModel(get(), get(), get(), get(), get())
    }

    viewModel {
        SceneListViewModel()
    }
    viewModel {
        SceneViewModel(get())
    }
}

fun appModules() = listOf(sinapsiaModule, characterServices, itemServices, viewModelModule)