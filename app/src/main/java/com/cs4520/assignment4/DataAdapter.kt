package com.cs4520.assignment4

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ProductItemBinding

class DataAdapter() : RecyclerView.Adapter<ProductViewHolder>() {
    var itemList = ArrayList<Product>()

    fun setData(data : ArrayList<ProductData>){
        this.itemList = convertData(data)
    }

    private fun convertData(data : ArrayList<ProductData>) : ArrayList<Product> {
        val convertedData = ArrayList<Product>()
        for(item in data){
            var newItem : Product
            if(item.type == "Equipment"){
                if(item.name == "" || item.price < 0){ //invalid answers, so we don't add it to the list
                    continue
                } else {
                    newItem = Product.EquipmentProduct(item.name, item.price)
                    if(!isInList(newItem, convertedData)){ //no repeats
                        convertedData.add(newItem)
                    }
                }
            } else if(item.type == "Food"){
                if(item.name == "" || item.price <0 || item.expiry == ""){
                    continue
                } else {
                    newItem = Product.FoodProduct(item.name, item.expiry, item.price)
                    if(!isInList(newItem, convertedData)){ //no repeats
                        convertedData.add(newItem)
                    }
                }
            } else { //It isn't a valid type, so we don't add it to the list
                continue
            }
        }
        return convertedData
    }

    private fun isInList(product : Product, list : ArrayList<Product>): Boolean{
        for(item in list){
            if(product == item){
                return true
            }
        }
        return false
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(layoutInflater)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
}