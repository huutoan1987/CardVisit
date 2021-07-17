package com.example.cardvisit.data.dao

import androidx.room.*
import com.example.cardvisit.data.entity.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cards: Card)

    @Query("DELETE FROM Tbl_Card")
    suspend fun deleteAll()

    @Update
    suspend fun update(card: Card)

    @Query("SELECT * FROM Tbl_Card ORDER BY localId DESC")
    fun getAllCardAsFlow(): Flow<List<Card>>
}