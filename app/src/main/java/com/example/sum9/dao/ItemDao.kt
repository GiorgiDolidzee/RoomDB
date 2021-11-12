package com.example.sum9.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sum9.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM ITEM")
    fun getAll(): Flow<List<Item>>

    @Insert
    suspend fun insertAll(vararg items: Item)

    @Delete
    fun delete(items: Item)

}