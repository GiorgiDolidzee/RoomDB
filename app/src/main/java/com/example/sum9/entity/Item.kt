package com.example.sum9.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ITEM")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "cover")
    val cover: String?,

    @ColumnInfo(name = "price")
    val price: String?,

    @ColumnInfo(name = "liked")
    val liked: Boolean

)