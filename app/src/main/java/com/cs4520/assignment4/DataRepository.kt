package com.cs4520.assignment4

class DataRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance()

    suspend fun getAllRepository() = retrofit.getProductList()
}