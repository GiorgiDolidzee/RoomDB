package com.example.sum9.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sum9.App
import com.example.sum9.dao.ItemDao
import com.example.sum9.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun itemDao() : ItemDao

    companion object {
        val db by lazy {
            Room.databaseBuilder(
                App.appContext!!,
                AppDataBase::class.java, "item_database"
            ).build()
        }
    }
}