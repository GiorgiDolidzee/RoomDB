package com.example.sum9.model


import com.google.gson.annotations.SerializedName

data class ItemModelItem(
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("liked")
    val liked: Boolean?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("title")
    val title: String?
)