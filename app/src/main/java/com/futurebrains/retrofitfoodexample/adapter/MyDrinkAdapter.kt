package com.futurebrains.retrofitfoodexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.futurebrains.retrofitfoodexample.databinding.DrinkDesingBinding
import com.futurebrains.retrofitfoodexample.model.User

class MyDrinkAdapter(val context: Context , val userlist: ArrayList<User>): RecyclerView.Adapter<MyDrinkAdapter.MyDrinkViewHolder>() {
    class MyDrinkViewHolder (val items : DrinkDesingBinding) : RecyclerView.ViewHolder(items.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDrinkViewHolder {
        return MyDrinkViewHolder(DrinkDesingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyDrinkViewHolder, position: Int) {
        Glide.with(context).load(userlist[position].image).into(holder.items.ivImage)
       holder.items.tvPrice.text = userlist[position].price
        holder.items.tvName.text = userlist[position].name
    }

    override fun getItemCount(): Int {
       return userlist.size
    }
}