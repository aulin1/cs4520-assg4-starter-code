package com.cs4520.assignment4

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ProductItemBinding

class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(
    binding.root) {
    fun bind(item: Product){
        when(item){
            is Product.FoodProduct -> {
                binding.itemName.text = item.name
                binding.itemExpDate.text = item.expiry
                binding.itemExpDate.visibility = View.VISIBLE
                val pricetext = "$" + item.price
                binding.itemPrice.text = pricetext
                binding.displayImage.setImageResource(R.drawable.foodphoto)
                binding.root.setBackgroundColor(Color.parseColor("#FFD965"))
            }
            is Product.EquipmentProduct -> {
                binding.itemName.text = item.name
                binding.itemExpDate.visibility = View.GONE
                val pricetext = "$" + item.price
                binding.itemPrice.text = pricetext
                binding.displayImage.setImageResource(R.drawable.equipmentphoto)
                binding.root.setBackgroundColor(Color.parseColor("#E06666"))
            }
        }
    }

}