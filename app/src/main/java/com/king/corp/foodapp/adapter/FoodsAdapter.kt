package com.king.corp.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.king.corp.foodapp.R
import com.king.corp.foodapp.models.Food
import kotlinx.android.synthetic.main.item_food.view.*

class FoodsAdapter(private val foods: ArrayList<Food>) : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food.imgUrl, food.name)
    }

    inner class FoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url: String?, name: String?) {
            itemView.food_name.text = name
            Glide.with(itemView)
                .load(url)
                .centerCrop()
                .into(itemView.food_img)
        }
    }
}