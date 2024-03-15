package com.cs4520.assignment4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class ProductViewModel() : ViewModel() {
    private val _ResponseData = MutableLiveData<ArrayList<ProductData>?>()
    val ResponseData : LiveData<ArrayList<ProductData>?> = _ResponseData
    private val repository = DataRepository()

    private val database = ProductDatabase.getInstance()

    init {
        makeApiCall()
    }


    private fun makeApiCall(input: String?=null) {
        //Log.d("Testing Room", "Entered API")
        val dao = database.productDao()

        viewModelScope.launch(Dispatchers.IO) {
            try{
                //Log.d("Testing Room", "Started loading")
                val response = repository.getAllRepository() //TA says that crashing here if nothing is loaded is ok
                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            //Log.d("Testing Room", "Loading Success")
                            _ResponseData.value = response.body()

                            response.body()?.let { dao.insertAll(it) }
                        } else {
                            _ResponseData.value = null
                        }
                    } catch (e: Throwable) {
                        _ResponseData.value = null
                    }
                }
            } catch (e : UnknownHostException){ //access database
                //Log.d("Testing Room", "No internet")
                withContext(Dispatchers.Main){
                    val databaseEntries = dao.getProducts()
                    if (databaseEntries.isEmpty()) {
                        //Log.d("Testing Room", "No Data")
                        _ResponseData.value = null
                    } else {
                        //Log.d("Testing Room", "Data Got")
                        val newData = ArrayList<ProductData>()
                        for(item in databaseEntries){
                            newData.add(item)
                        }
                        _ResponseData.value = newData
                    }
                }
            }
        }
    }
}
