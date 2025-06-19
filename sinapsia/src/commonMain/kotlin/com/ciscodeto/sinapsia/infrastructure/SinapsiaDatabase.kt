@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.ciscodeto.sinapsia.infrastructure

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.ciscodeto.sinapsia.infrastructure.action.ActionCounterCrossRef
import com.ciscodeto.sinapsia.infrastructure.action.ActionDao
import com.ciscodeto.sinapsia.infrastructure.action.ActionEntity
import com.ciscodeto.sinapsia.infrastructure.character.CharacterDao
import com.ciscodeto.sinapsia.infrastructure.character.CharacterEntity
import com.ciscodeto.sinapsia.infrastructure.item.ItemDao
import com.ciscodeto.sinapsia.infrastructure.item.ItemEntity

@Database(
    entities = [CharacterEntity::class,
        ItemEntity::class,
        ActionEntity::class,
        ActionCounterCrossRef::class],
    version = 4,
    exportSchema = true
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SinapsiaDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun itemDao(): ItemDao
    abstract fun actionDao(): ActionDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SinapsiaDatabase> {
    override fun initialize(): SinapsiaDatabase
}