package com.cs4520.assignment4

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
            //TODO: Repeat check + valid field check
            var newItem : Product
            if(item.type == "Equipment"){
                newItem = Product.EquipmentProduct(item.name, item.price)
                convertedData.add(newItem)
            } else if(item.type == "Food"){
                newItem = Product.FoodProduct(item.name, item.expiry, item.price)
                convertedData.add(newItem)
            }
        }
        return convertedData
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