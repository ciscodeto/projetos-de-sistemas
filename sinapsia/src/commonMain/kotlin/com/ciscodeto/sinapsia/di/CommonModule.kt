package com.ciscodeto.sinapsia.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.application.character.repository.CharacterRepository
import org.koin.dsl.module

val sinapsiaModule = module {
    // Infra
    single { RoomDatabase.Builder.data(get(), AppDatabase::class.java, "sinapsia.db").build() }
    single { get<AppDatabase>().characterDao() }

    // Repository
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    // Use cases
    single { CreateCharacterUseCase(get()) }
    single { GetCharacterUseCase(get()) }
}
