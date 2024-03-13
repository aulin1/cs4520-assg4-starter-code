package com.cs4520.assignment4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
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
        val data = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)

        repository.getAllRepository().enqueue(object : retrofit2.Callback<ArrayList<ProductData>> {
            override fun onFailure(call: Call<ArrayList<ProductData>>, t: Throwable) {
                Log.d("Testing API", t.toString())
                _ResponseData.value = null
            }

            override fun onResponse(
                call: Call<ArrayList<ProductData>>,
                response: Response<ArrayList<ProductData>>
            ) {
                Log.d("Testing API", "Succeeded")
                if (!response.isSuccessful) _ResponseData.value =
                    null else _ResponseData.value = response.body()
            }
        })
    }
}
