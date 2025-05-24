package com.ciscodeto.sinapsia.infrastructure.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getById(id: ByteArray): CharacterEntity?

    @Query("SELECT * FROM characters WHERE name = :name")
    suspend fun getByName(name: String): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Upsert
    suspend fun upsert(character: CharacterEntity)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun delete(id: ByteArray)
}
