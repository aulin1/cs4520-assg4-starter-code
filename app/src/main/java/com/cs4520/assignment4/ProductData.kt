package com.cs4520.assignment4

import com.google.gson.annotations.SerializedName

sealed class Product(name: String, type: String, expiry: String?, price: Float){

    //types of products
    data class FoodProduct(val name: String, val expiry: String, val price: Float) : Product(name = name, "Food", expiry = expiry, price = price)
    data class EquipmentProduct(val name: String, val price: Float) : Product(name = name, "Equipment", expiry = null, price = price)
}

class ProductData{
    @SerializedName("name")
    var name = ""
    @SerializedName("type")
    var type = ""
    @SerializedName("price")
    var price: Float = -1.0f
    @SerializedName("expiry")
    var expiry = ""
}