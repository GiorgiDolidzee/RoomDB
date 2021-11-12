package com.example.sum9.network

import com.example.sum9.model.ItemModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getItems(): Response<List<ItemModel>>

}