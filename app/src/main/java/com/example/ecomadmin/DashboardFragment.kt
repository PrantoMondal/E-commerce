package com.example.ecomadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomadmin.adapters.DashboardItemAdapters
import com.example.ecomadmin.databinding.DashboardRowBinding
import com.example.ecomadmin.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        val adapter = DashboardItemAdapters()
        val glm = GridLayoutManager(requireActivity(),2)
        binding.dashboardRV.layoutManager = glm
        binding.dashboardRV.adapter = adapter


        return binding.root
    }

}