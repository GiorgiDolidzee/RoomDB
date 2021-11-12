package com.example.sum9.dao

import androidx.room.*
import com.example.sum9.model.ItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM ITEM")
    fun getAll(): Flow<List<ItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: ItemModel)

    @Delete
    fun delete(items: List<ItemModel>)

    @Query("DELETE FROM ITEM")
    fun deleteAll()
}