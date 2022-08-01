package com.absut.isro.archive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.absut.isro.archive.data.model.Spacecraft
import com.absut.isro.archive.data.model.SpacecraftList
import com.absut.isro.archive.databinding.SpacecraftListItemBinding

class SpacecraftAdapter() : ListAdapter<Spacecraft, SpacecraftViewHolder>(DiffCallback()) {

    //  private val spacecrafts = ArrayList<Spacecraft>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpacecraftViewHolder {
        val binding =
            SpacecraftListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpacecraftViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpacecraftViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = currentList.size

    /* fun setData(spacecraft: List<Spacecraft>) {
         spacecrafts.clear()
         spacecrafts.addAll(spacecraft)
     }*/


}

class SpacecraftViewHolder(val binding: SpacecraftListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(spacecraft: Spacecraft) {
        binding.apply {
            txtId.text = spacecraft.id.toString()
            txtName.text = spacecraft.name
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<Spacecraft>() {
    override fun areItemsTheSame(oldItem: Spacecraft, newItem: Spacecraft): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Spacecraft, newItem: Spacecraft): Boolean {
        return oldItem == newItem
    }

}