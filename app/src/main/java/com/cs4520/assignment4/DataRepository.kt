package com.cs4520.assignment4

class DataRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)

    fun getAllRepository() = retrofit.getProductList()
}