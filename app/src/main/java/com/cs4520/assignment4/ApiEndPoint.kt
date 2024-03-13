package com.cs4520.assignment4

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {
    @GET(Api.ENDPOINT)
    //fun getProductList(@Query("q") q : String) : Call<ResponseModel>
    suspend fun getProductList() : Response<ArrayList<ProductData>>
}