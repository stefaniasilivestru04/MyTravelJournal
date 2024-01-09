package com.example.mytraveljournal

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class TravelMemoryAdapter(var memories: List<TravelMemory>) :
        RecyclerView.Adapter<TravelMemoryAdapter.TravelMemoryViewHolder>() {

    inner class TravelMemoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val dateOfTravel: TextView = view.findViewById(R.id.dateOfTravel)
        val isFavorite: ToggleButton = view.findViewById(R.id.isFavorite)
        val detailsButton: Button = view.findViewById(R.id.detailsButton)
        val editButton: Button = view.findViewById(R.id.editButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelMemoryViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_memory, parent, false)
        return TravelMemoryViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return memories.size
    }

    override fun onBindViewHolder(holder: TravelMemoryViewHolder, position: Int) {
        val memory = memories[position]
        holder.placeName.text = memory.placeName
        holder.dateOfTravel.text = memory.dateOfTravel
        holder.isFavorite.isChecked = memory.isFavorite
        holder.isFavorite.setOnCheckedChangeListener { _, isChecked ->
            memory.isFavorite = isChecked
            if (isChecked) {
                holder.isFavorite.setBackgroundResource(R.color.is_favorite)
            } else {
                holder.isFavorite.setBackgroundResource(R.color.background_color)
            }
        }

        holder.detailsButton.setOnClickListener {
            Log.d("button", "Details button clicked")
            findNavController(holder.itemView).navigate(R.id.action_nav_home_to_nav_details_memory)
        }

        holder.editButton.setOnClickListener {
            Log.d("button", "Edit button clicked")
            findNavController(holder.itemView).navigate(R.id.action_nav_home_to_nav_edit_memory)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<TravelMemory>) {
        memories = newData.toMutableList()
        notifyDataSetChanged()
    }


}

