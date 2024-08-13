package com.absut.isro.archive.ui.centres

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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.absut.isro.archive.R
import com.absut.isro.archive.data.model.Centre
import com.absut.isro.archive.data.model.Launcher
import com.absut.isro.archive.databinding.FragmentCentersBinding
import com.absut.isro.archive.ui.MainActivity
import com.absut.isro.archive.ui.components.common.ErrorView
import com.absut.isro.archive.ui.components.common.ProgressView
import com.absut.isro.archive.ui.viewmodel.ISROViewModel
import com.absut.isro.archive.utils.Resource
import com.absut.isro.archive.utils.State
import com.example.compose.AppTheme
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CentersFragment : Fragment() {

    private val viewModel: ISROViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getCentres()

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    CentersScreen()
                }
            }
        }
    }

    @Composable
    fun CentersScreen(modifier: Modifier = Modifier) {
        val centers = viewModel.centres

        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surface
        ) {
            when (centers) {
                is State.Loading -> {
                    ProgressView()
                }

                is State.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        items(centers.data) {
                            CenterListItem(item = it)
                        }
                    }
                }

                is State.Error -> {
                    ErrorView(text = centers.message) {
                        viewModel.getCentres()
                    }

                }
            }

        }
    }

    @Composable
    fun CenterListItem(modifier: Modifier = Modifier, item: Centre) {
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
                        text = item.id.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_regular))
                    )
                    Text(
                        text = item.name.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = item.place.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = item.state.toString(),
                        Modifier
                            .padding(end = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_hub_black_24dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceVariant,
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
    private fun CenterListItemPreview() {
        AppTheme {
            CenterListItem(
                item = Centre(
                    1,
                    "Semi-Conductor Laboratory (SCL)",
                    "Chandigarh",
                    "Punjab/Haryana"
                )
            )
        }
    }

    @Preview
    @Composable
    private fun CentersScreenPreview() {
        AppTheme {
           /* CentersScreen(list = List(20) { Centre(
                    it,
                    "Center name $it",
                    "Place $it",
                    "State name $it"
                )
            })*/
        }
    }

}