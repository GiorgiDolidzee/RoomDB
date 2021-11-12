package com.example.sum9.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ITEM")
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "cover")
    @SerializedName("cover")
    val cover: String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: String?,

    @ColumnInfo(name = "liked")
    @SerializedName("liked")
    val liked: Boolean?


)