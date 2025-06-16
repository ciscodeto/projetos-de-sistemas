package com.ciscodeto.sinapsia.infrastructure.action

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {
    @Query("SELECT * FROM actions")
    fun getAll(): Flow<List<ActionEntity>>

    @Query("SELECT * FROM actions WHERE id = :id")
    suspend fun getById(id: ByteArray): ActionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(action: ActionEntity)

    @Update
    suspend fun update(action: ActionEntity)

    @Query("DELETE FROM actions WHERE id = :id")
    suspend fun deleteById(id: ByteArray)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounterRefs(refs: List<ActionCounterCrossRef>)

    @Query("SELECT counterActionId FROM action_counter_cross_ref WHERE actionId = :actionId")
    suspend fun getCounterActionIds(actionId: ByteArray): List<ByteArray>

    @Query("SELECT * FROM actions WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<ByteArray>): List<ActionEntity>

    @Query("DELETE FROM action_counter_cross_ref WHERE actionId = :actionId")
    suspend fun deleteCounterRefsByActionId(actionId: ByteArray)
}
