package com.ciscodeto.sinapsia.infrastructure.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterEntity)
}
