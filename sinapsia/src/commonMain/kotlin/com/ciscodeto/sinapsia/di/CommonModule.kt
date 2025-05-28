package com.ciscodeto.sinapsia.di

import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import com.ciscodeto.sinapsia.application.item.repository.ItemRepository
import com.ciscodeto.sinapsia.infrastructure.CreateDatabase
import com.ciscodeto.sinapsia.infrastructure.SinapsiaDatabase
import com.ciscodeto.sinapsia.infrastructure.character.CharacterDao
import com.ciscodeto.sinapsia.infrastructure.character.CharacterRepositoryRoomImpl
import com.ciscodeto.sinapsia.infrastructure.item.ItemDao
import com.ciscodeto.sinapsia.infrastructure.item.ItemRepositoryRoomImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val sinapsiaModule = module {
    single<RoomDatabase.Builder<SinapsiaDatabase>> { getDatabaseBuilder() }

    single<SinapsiaDatabase> { CreateDatabase(get()).getDatabase() }

    // Infra
    single<CharacterDao> { get<SinapsiaDatabase>().characterDao() }
    single<ItemDao> { get<SinapsiaDatabase>().itemDao() }

    // Repository
    single<CharacterRepository> { CharacterRepositoryRoomImpl(get()) }

    single<ItemRepository> { ItemRepositoryRoomImpl(get()) }
}

expect fun getDatabaseBuilder() : RoomDatabase.Builder<SinapsiaDatabase>