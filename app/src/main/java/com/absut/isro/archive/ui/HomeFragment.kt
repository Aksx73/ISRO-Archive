package com.absut.isro.archive.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import com.absut.isro.archive.R
import com.absut.isro.archive.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*_binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ComposeContent()
                }
            }
        }

        return binding.root*/


        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    HomeScreen()
                }
            }

        }


    }

    @Composable
    private fun HomeScreen(modifier: Modifier = Modifier) {
        Column(modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            ContentView(
                title = R.string.spacecraft_label,
                desc = R.string.spacecraft_desc,
                image = R.drawable.ic_rocket_black_24dp
            )
            Spacer(modifier = Modifier.height(12.dp))
            ContentView(
                title = R.string.launchers_label,
                desc = R.string.launchers_desc,
                image = R.drawable.ic_rocket_launch_black_24dp
            )
            Spacer(modifier = Modifier.height(12.dp))
            ContentView(
                title = R.string.customer_satellite_label,
                desc = R.string.customer_satellite_desc,
                image = R.drawable.ic_satellite_black_24dp
            )
            Spacer(modifier = Modifier.height(12.dp))
            ContentView(
                title = R.string.centres_label,
                desc = R.string.centres_desc,
                image = R.drawable.ic_hub_black_24dp
            )
        }

    }

    @Composable
    private fun ContentView(
        modifier: Modifier = Modifier,
        @StringRes title: Int,
        @StringRes desc: Int,
        @DrawableRes image: Int,
        clickListener: () -> Unit = {}
    ) {
        Surface(
            shape = Shapes().small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            color = MaterialTheme.colorScheme.surface,
            onClick = clickListener
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = stringResource(title),
                        style = MaterialTheme.typography.headlineSmall,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                    Text(
                        text = stringResource(desc),
                        modifier = Modifier.padding(top = 8.dp),
                        maxLines = 3,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_regular)),
                        fontWeight = FontWeight.Normal
                    )
                }
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*  binding.cardCenters.setOnClickListener {
              findNavController().navigate(R.id.action_HomeFragment_to_centersFragment)
          }

          binding.cardLaunchers.setOnClickListener {
              findNavController().navigate(R.id.action_HomeFragment_to_launchersFragment)
          }

          binding.cardSpacecraft.setOnClickListener {
              findNavController().navigate(R.id.action_HomeFragment_to_spacecraftFragment)
          }

        binding.cardCustomerSatellites.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_customerSatellitesFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}