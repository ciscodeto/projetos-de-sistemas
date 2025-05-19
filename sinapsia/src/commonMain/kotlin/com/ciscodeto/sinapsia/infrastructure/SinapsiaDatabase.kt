package com.ciscodeto.sinapsia.infrastructure

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ciscodeto.sinapsia.infrastructure.character.CharacterDao
import com.ciscodeto.sinapsia.infrastructure.character.CharacterEntity
import com.ciscodeto.sinapsia.infrastructure.item.ItemEntity

@Database(entities = [CharacterEntity::class, ItemEntity::class], version = 1, exportSchema = true)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SinapsiaDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SinapsiaDatabase> {
    override fun initialize(): SinapsiaDatabase
}