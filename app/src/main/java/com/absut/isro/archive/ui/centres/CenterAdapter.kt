package com.absut.isro.archive.ui.centres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.absut.isro.archive.data.model.Centre
import com.absut.isro.archive.databinding.CenterListItemBinding

/** Right implementation**/
class CenterAdapter : ListAdapter<Centre, CenterAdapter.CentreViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CentreViewHolder {
        val binding =
            CenterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CentreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CentreViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class CentreViewHolder(val binding: CenterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(centre: Centre) {
            binding.apply {
                txtId.text = centre.id.toString()
                txtName.text = centre.name
                txtPlace.text = centre.place
                txtState.text = centre.state
            }
        }
    }

    //DiffUtils
    companion object {
        private val callback = object : DiffUtil.ItemCallback<Centre>() {
            override fun areItemsTheSame(oldItem: Centre, newItem: Centre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Centre, newItem: Centre): Boolean {
                return oldItem == newItem
            }

        }
    }

}

