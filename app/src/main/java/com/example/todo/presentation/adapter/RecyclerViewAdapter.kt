package com.example.todo.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.domain.model.todo.Todo


internal class RecyclerViewAdapter(private var itemsList: List<Todo?>?) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView1: TextView = view.findViewById(R.id.tvItem1)
        var itemTextView2: TextView = view.findViewById(R.id.tvItem2)
        var itemTextView3: TextView = view.findViewById(R.id.tvItem3)
        var itemTextView4: TextView = view.findViewById(R.id.tvItem4)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemTextView1.text = item?.title
        holder.itemTextView2.text = item?.description
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }
}

