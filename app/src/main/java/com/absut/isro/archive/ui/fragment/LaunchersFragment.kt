package com.absut.isro.archive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.R
import com.absut.isro.archive.databinding.FragmentLaunchersBinding
import com.absut.isro.archive.databinding.FragmentSpacecraftBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.adapter.LauncherAdapter
import com.absut.isro.archive.ui.adapter.SpacecraftAdapter
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.newsapiclient.domain.util.Resource
import com.google.android.material.snackbar.Snackbar

class LaunchersFragment : Fragment() {

    private var _binding: FragmentLaunchersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ISROViewModel
    private lateinit var adapterLauncher: LauncherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLaunchersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        getLaunchers()

    }

    private fun getLaunchers() {
        viewModel.getLaunchers()
        viewModel.launchers.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgress()
                    it.data?.let { launcherList ->
                        adapterLauncher.differ.submitList(launcherList.launchers.toList())
                    }
                }
                is Resource.Loading -> {
                    showProgress()
                }
                is Resource.Error -> {
                    hideProgress()
                    Snackbar.make(
                        binding.recyclerView,
                        it.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun setupRecyclerView() {
        adapterLauncher = LauncherAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterLauncher
            itemAnimator = DefaultItemAnimator()
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