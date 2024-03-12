package com.cs4520.assignment4

import com.google.gson.annotations.SerializedName

data class ResponseModel(val items: ArrayList<ProductData>)

sealed class Product(name: String, type: String, expiry: String?, price: Int){

    //types of products
    data class FoodProduct(val name: String, val expiry: String, val price: Int) : Product(name = name, "Food", expiry = expiry, price = price)
    data class EquipmentProduct(val name: String, val price: Int) : Product(name = name, "Equipment", expiry = null, price = price)
}

class ProductData{
    @SerializedName("name")
    var name = ""
    @SerializedName("type")
    var type = ""
    @SerializedName("price")
    var price: Int = -1
    @SerializedName("expiry")
    var expiry = ""
}