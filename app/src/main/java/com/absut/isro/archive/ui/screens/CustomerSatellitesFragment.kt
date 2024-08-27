package com.absut.isro.archive.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import com.absut.isro.archive.ui.components.ErrorView
import com.absut.isro.archive.ui.components.ProgressView
import com.absut.isro.archive.ui.components.SatelliteListItem
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme

class CustomerSatellitesFragment : Fragment() {

    private val viewModel: ISROViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        viewModel.getCustomerSatellites()
       return ComposeView(requireContext()).apply {
           setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
           setContent {
               AppTheme {
                   SatelliteScreen()
               }
           }
       }
    }

    @Composable
    fun SatelliteScreen(modifier: Modifier = Modifier) {
        val satellite = viewModel.customerSatellites

        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface
        ) {
            when (satellite) {
                is State.Loading -> {
                    ProgressView()
                }

                is State.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        items(satellite.data) {
                            SatelliteListItem(item = it)
                        }
                    }
                }

                is State.Error -> {
                    ErrorView(text = satellite.message) {
                        viewModel.getCustomerSatellites()
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    private fun SpacecraftScreenPreview() {
        AppTheme {
            //SatelliteScreen(list = List(20) { CustomerSatellite(1, "Germany","DLR-TUBSAT","26-05-1989","PSLV-C2","45") })
        }
    }

}