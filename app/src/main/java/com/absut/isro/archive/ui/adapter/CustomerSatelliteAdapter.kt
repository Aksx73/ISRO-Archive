package com.absut.isro.archive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.absut.isro.archive.data.model.CustomerSatellite
import com.absut.isro.archive.databinding.SatelliteListItemBinding

/** Right implementation**/
class CustomerSatelliteAdapter :
    ListAdapter<CustomerSatellite, CustomerSatelliteAdapter.CustomerSatelliteViewHolder>(callback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerSatelliteViewHolder {
        val binding =
            SatelliteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerSatelliteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerSatelliteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    //View model
    inner class CustomerSatelliteViewHolder(val binding: SatelliteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(satellite: CustomerSatellite) {
            binding.apply {
                txtName.text = satellite.id
                txtCountry.text = satellite.country
                txtLaunchDate.text = satellite.launchDate
                txtMass.text = satellite.mass
                txtLauncher.text = satellite.launcher
            }
        }
    }

    //DiffUtils
    companion object {
        private val callback = object : DiffUtil.ItemCallback<CustomerSatellite>() {
            override fun areItemsTheSame(
                oldItem: CustomerSatellite,
                newItem: CustomerSatellite
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CustomerSatellite,
                newItem: CustomerSatellite
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


}