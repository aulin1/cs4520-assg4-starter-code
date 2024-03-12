package com.cs4520.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class ProductViewModel(private val repository: DataRepository) : ViewModel() {
    private val _ResponseData = MutableLiveData<ResponseModel?>()
    val ResponseData : LiveData<ResponseModel?> = _ResponseData

    private var dataAdapter: DataAdapter = DataAdapter()

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


    private fun makeApiCall(input: String?=null) { //TODO: coroutines
        val data = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)
        repository.getAllRepository().enqueue(object : retrofit2.Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                _ResponseData.value = null
                //TODO: add error?
            }

            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (!response.isSuccessful) _ResponseData.value =
                    null else _ResponseData.value = response.body()
            }
        })
    }
}
