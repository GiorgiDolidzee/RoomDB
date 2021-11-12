package com.example.sum9.fragments.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sum9.database.AppDataBase
import com.example.sum9.model.ItemModel
import com.example.sum9.network.Network
import com.example.sum9.network.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _myData: MutableStateFlow<UiState> = MutableStateFlow(UiState(error = UiState.Error.ClientError))
    val myData: MutableStateFlow<UiState> get() = _myData

    val items: Flow<List<ItemModel>> = AppDataBase.db.itemDao().getAll()

    private suspend fun getData(): Resource<List<ItemModel>> {
        return try {
            val response = Network.retrofit.getItems()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                addItem(result)
                Resource.Success(result)
            } else {
                Resource.Error("Response Error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }


    init {
        setData()
    }


    fun setData() {
        viewModelScope.launch {
            val data = getData()
            when (data) {
                is Resource.Success -> {
                    _myData.value = UiState(items = data.data)
                }
                is Resource.Error -> {
                    _myData.value = UiState(error = UiState.Error.ClientError)
                }
            }
        }
    }


    data class UiState(
        val error: Error? = null,
        val items: List<ItemModel>? = null,
    ) {
        sealed class Error {
            object ClientError : Error()
        }
    }


    fun addItem(data: List<ItemModel>) {
        viewModelScope.launch {
            deleteAll()
            AppDataBase.db.itemDao().insertAll(*data.toTypedArray())
        }
    }

    fun deleteAll() {
        viewModelScope.launch(IO) {
            AppDataBase.db.itemDao().deleteAll()
        }
    }



    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                return false
            }
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }





}
