package com.absut.isro.archive.ui.satellite

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.R
import com.absut.isro.archive.data.model.CustomerSatellite
import com.absut.isro.archive.data.model.Spacecraft
import com.absut.isro.archive.databinding.FragmentCustomerSatellitesBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.Resource
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

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
        getCustomerSatellites()
        collectSatellites()

    }

    private fun getCustomerSatellites() = viewModel.getCustomerSatellites()

    private fun collectSatellites() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.customerSatellites.collect { state ->
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
                                adapterSatellite.submitList(state.data.toMutableList())
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

    @Composable
    fun SatelliteScreen(modifier: Modifier = Modifier, list: List<CustomerSatellite>) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
            ) {
                items(list) {
                    SatelliteListItem(item = it)
                }
            }
        }
    }

    @Composable
    fun SatelliteListItem(modifier: Modifier = Modifier, item: CustomerSatellite) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = Shapes().small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            //color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = item.country.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = item.launchDate.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = item.mass.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = item.launcher.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )

                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_rocket_black_24dp),
                    contentDescription = null,
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                )
            }
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
    @Composable
    private fun SpacecraftListItemPreview() {
        AppTheme {
            SatelliteListItem(item = CustomerSatellite(1, "Germany","DLR-TUBSAT","26-05-1989","PSLV-C2","45"))
        }
    }

    @Preview
    @Composable
    private fun SpacecraftScreenPreview() {
        AppTheme {
            SatelliteScreen(list = List(20) { CustomerSatellite(1, "Germany","DLR-TUBSAT","26-05-1989","PSLV-C2","45") })
        }
    }

}