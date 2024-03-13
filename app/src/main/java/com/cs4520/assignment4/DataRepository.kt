package com.cs4520.assignment4

class DataRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)

    suspend fun getAllRepository() = retrofit.getProductList()
}