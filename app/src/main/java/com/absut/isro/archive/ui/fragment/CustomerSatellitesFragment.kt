package com.absut.isro.archive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.R
import com.absut.isro.archive.databinding.FragmentCentersBinding
import com.absut.isro.archive.databinding.FragmentCustomerSatellitesBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.adapter.CenterAdapter
import com.absut.isro.archive.ui.adapter.CustomerSatelliteAdapter
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.newsapiclient.domain.util.Resource
import com.google.android.material.snackbar.Snackbar

class CustomerSatellitesFragment : Fragment() {

    private var _binding: FragmentCustomerSatellitesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ISROViewModel
    private lateinit var adapterSatellite: CustomerSatelliteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerSatellitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        initRecyclerView()
        getSatellites()

    }

    private fun getSatellites() {
        viewModel.getCustomerSatellites()
        viewModel.customerSatellites.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgress()
                    it.data?.let { satelliteList ->
                        adapterSatellite.submitList(satelliteList.customerSatellites.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgress()
                    Snackbar.make(
                        binding.recyclerView,
                        it.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    showProgress()
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapterSatellite = CustomerSatelliteAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterSatellite
        }
    }

    private fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressCircular.visibility = View.INVISIBLE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}