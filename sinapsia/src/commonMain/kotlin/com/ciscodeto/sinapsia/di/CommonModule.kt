package com.ciscodeto.sinapsia.di

import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.item.repository.ItemRepository
import com.ciscodeto.sinapsia.domain.actions.Action
import com.ciscodeto.sinapsia.domain.character.Character
import com.ciscodeto.sinapsia.domain.dice.DiceClash
import com.ciscodeto.sinapsia.infrastructure.CreateDatabase
import com.ciscodeto.sinapsia.infrastructure.SinapsiaDatabase
import com.ciscodeto.sinapsia.infrastructure.action.ActionDao
import com.ciscodeto.sinapsia.infrastructure.action.ActionRepositoryRoomImpl
import com.ciscodeto.sinapsia.infrastructure.character.CharacterDao
import com.ciscodeto.sinapsia.infrastructure.character.CharacterRepositoryRoomImpl
import com.ciscodeto.sinapsia.infrastructure.item.ItemDao
import com.ciscodeto.sinapsia.infrastructure.item.ItemRepositoryRoomImpl
import org.koin.dsl.module

val sinapsiaModule = module {
    factory { (actor: Character, action: Action, target: Character?) ->
        DiceClash(
            actor = actor,
            initialAction = action,
            target = target,
            updateCharacter = get()
        )
    }

    single<RoomDatabase.Builder<SinapsiaDatabase>> { getDatabaseBuilder() }

    single<SinapsiaDatabase> { CreateDatabase(get()).getDatabase() }

    // Infra
    single<CharacterDao> { get<SinapsiaDatabase>().characterDao() }
    single<ItemDao> { get<SinapsiaDatabase>().itemDao() }
    single<ActionDao> { get<SinapsiaDatabase>().actionDao() }

    // Repository
    single<CharacterRepository> { CharacterRepositoryRoomImpl(get()) }
    single<ActionRepository> { ActionRepositoryRoomImpl(get()) }
    single<ItemRepository> { ItemRepositoryRoomImpl(get()) }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase>