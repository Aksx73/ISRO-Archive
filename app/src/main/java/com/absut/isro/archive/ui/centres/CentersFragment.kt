package com.absut.isro.archive.ui.centres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.databinding.FragmentCentersBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.Resource
import com.google.android.material.snackbar.Snackbar

class CentersFragment : Fragment() {

    private var _binding: FragmentCentersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ISROViewModel
    private lateinit var adapterCentre: CenterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCentersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        initRecyclerView()
        getCenteres()

    }

    private fun getCenteres() {
        viewModel.getCenters()
        viewModel.centers.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgress()
                    binding.emptyState.visibility = View.GONE
                    it.data?.let { centersList ->
                        adapterCentre.submitList(centersList.centres.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgress()
                    binding.emptyState.visibility = View.GONE
                    Snackbar.make(
                        binding.recyclerView,
                        it.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    binding.emptyState.visibility = View.GONE
                    showProgress()
                }
                is Resource.NoConnection -> {
                    binding.emptyState.visibility = View.VISIBLE
                    binding.btRetry.setOnClickListener{
                        viewModel.getCenters()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapterCentre = CenterAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterCentre
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