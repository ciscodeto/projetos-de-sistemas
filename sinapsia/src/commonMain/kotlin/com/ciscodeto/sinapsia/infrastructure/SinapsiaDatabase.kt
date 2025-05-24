@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.ciscodeto.sinapsia.infrastructure

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ciscodeto.sinapsia.infrastructure.character.CharacterDao
import com.ciscodeto.sinapsia.infrastructure.character.CharacterEntity
import com.ciscodeto.sinapsia.infrastructure.item.ItemDao
import com.ciscodeto.sinapsia.infrastructure.item.ItemEntity

@Database(entities = [CharacterEntity::class, ItemEntity::class], version = 3, exportSchema = true)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SinapsiaDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun itemDao(): ItemDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SinapsiaDatabase> {
    override fun initialize(): SinapsiaDatabase
}