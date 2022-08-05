package com.absut.isro.archive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.absut.isro.archive.data.model.Launcher
import com.absut.isro.archive.data.model.Spacecraft
import com.absut.isro.archive.databinding.LauncherListItemBinding
import com.absut.isro.archive.databinding.SpacecraftListItemBinding

class LauncherAdapter() : RecyclerView.Adapter<LauncherViewHolder>() {

    private val DiffCallback = object : DiffUtil.ItemCallback<Launcher>() {
        override fun areItemsTheSame(oldItem: Launcher, newItem: Launcher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Launcher, newItem: Launcher): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherViewHolder {
        val binding =
            LauncherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LauncherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LauncherViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = differ.currentList.size

}

class LauncherViewHolder(val binding: LauncherListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(launcher: Launcher) {
        binding.txtName.text = launcher.name
    }
}


