package com.example.ecomadmin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomadmin.databinding.DashboardRowBinding
import com.example.ecomadmin.models.DashboardItem
import com.example.ecomadmin.models.dashboardItemList

class DashboardItemAdapters : RecyclerView.Adapter<DashboardItemAdapters.DashboardViewHolder>() {

    class DashboardViewHolder(val binding:DashboardRowBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(item:DashboardItem){
                binding.item = item
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = DashboardRowBinding.inflate(
            LayoutInflater.from(parent.context), parent,false
        )
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
       holder.bind(dashboardItemList.get(position))
    }

    override fun getItemCount(): Int  = dashboardItemList.size


}