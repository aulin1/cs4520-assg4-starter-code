package com.cs4520.assignment4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class ProductViewModel() : ViewModel() {
    private val _ResponseData = MutableLiveData<ArrayList<ProductData>?>()
    val ResponseData : LiveData<ArrayList<ProductData>?> = _ResponseData
    private val repository = DataRepository()

    private var dataAdapter = DataAdapter()

    init {
        makeApiCall()
    }
    fun getAdapter(): DataAdapter {
        return dataAdapter
    }

    fun setAdapterData(data : ArrayList<ProductData>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }


    private fun makeApiCall(input: String?=null) {
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getAllRepository()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        _ResponseData.value = response.body()
                    } else {
                        _ResponseData.value = null
                    }
                }  catch (e: Throwable) {
                    Log.d("Testing API", e.toString())
                    _ResponseData.value = null
                }
            }
        }
    }
}
