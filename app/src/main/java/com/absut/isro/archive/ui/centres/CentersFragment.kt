package com.absut.isro.archive.ui.centres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.databinding.FragmentCentersBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.Resource
import com.absut.isro.archive.utils.State
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

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
        collectCentres()

    }

    private fun getCenteres() = viewModel.getCentres()


    private fun collectCentres() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.centres.collect { state ->
                    when (state) {
                        is State.Error -> {
                            binding.emptyState.visibility = View.GONE
                            hideProgress()
                            Snackbar.make(binding.recyclerView, state.message, Snackbar.LENGTH_SHORT).show()
                        }
                        is State.Loading -> {
                            binding.emptyState.visibility = View.GONE
                            showProgress()
                        }
                        is State.Success -> {
                            if (state.data.isNotEmpty()) {
                                adapterCentre.submitList(state.data.toMutableList())
                                binding.emptyState.visibility = View.GONE
                                hideProgress()
                            }
                        }
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