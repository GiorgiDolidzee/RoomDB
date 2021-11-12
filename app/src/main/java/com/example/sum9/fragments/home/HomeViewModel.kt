package com.example.sum9.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sum9.database.AppDataBase
import com.example.sum9.entity.Item
import com.example.sum9.model.ItemModel
import com.example.sum9.network.Network
import com.example.sum9.network.Resource
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _data = MutableLiveData<Resource<ItemModel>?>()
    val data: MutableLiveData<Resource<ItemModel>?> get() = _data

    suspend fun getItems() : Resource<ItemModel> {
        return try {
            val response = Network.retrofit.getItems()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    fun setData() {
        viewModelScope.launch {
            when(getItems()) {
                is Resource.Success -> {
                    _data.postValue(getItems())
                }

                is Resource.Error -> {
                    _data.postValue(Resource.Error("ERROR"))
                }
            }
        }
    }


    fun addItem(title: String, cover: String, price: String, liked: Boolean) {
        if(title.isNotEmpty() && cover.isNotEmpty() && price.isNotEmpty()) {
            viewModelScope.launch {
                AppDataBase.db.itemDao().insertAll(Item(0, title, cover, price, liked))
            }
        }
    }

}
