package com.absut.isro.archive.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.absut.isro.archive.R
import com.absut.isro.archive.ui.screens.CentersScreen
import com.absut.isro.archive.ui.screens.HomeScreen
import com.absut.isro.archive.ui.screens.LauncherScreen
import com.absut.isro.archive.ui.screens.SatelliteScreen
import com.absut.isro.archive.ui.screens.SpacecraftScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val routeToTitleMap = mapOf(
        ROUTE_HOME to "ISRO \uD83D\uDE80 Archive",
        ROUTE_SPACECRAFT to "Spacecrafts",
        ROUTE_LAUNCHER to "Launchers",
        ROUTE_SATELLITE to "Customer Satellites",
        ROUTE_CENTRE to "Centres"
    )

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_SPACECRAFT = "spacecraft"
        const val ROUTE_LAUNCHER = "launchers"
        const val ROUTE_SATELLITE = "satellite"
        const val ROUTE_CENTRE = "centre"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainScreen()
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(viewModel: ISROViewModel = viewModel()) {
        val navController = rememberNavController()
        var showMenu by remember { mutableStateOf(false) }
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = routeToTitleMap[currentDestination?.route]
                            ?: stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.roboto_mono_medium))
                    )
                }, actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                // Handle item click
                                showMenu = false
                            },
                            text = { Text(text = "About") }
                        )
                    }
                },
                    navigationIcon = {
                        if (currentDestination?.route != "home") {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                    contentDescription = "Navigation up"
                                )
                            }
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ROUTE_HOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(ROUTE_HOME) {
                    HomeScreen(navController = navController)
                }
                composable(ROUTE_SPACECRAFT) {
                    SpacecraftScreen(
                        viewModel = viewModel
                    )
                }
                composable(ROUTE_SATELLITE) {
                    SatelliteScreen(
                        viewModel = viewModel
                    )
                }
                composable(ROUTE_LAUNCHER) {
                    LauncherScreen(
                        viewModel = viewModel
                    )
                }
                composable(ROUTE_CENTRE) {
                    CentersScreen(
                        viewModel = viewModel
                    )
                }
            }

        }

    }

    @Preview
    @Composable
    private fun MainScreenPreview() {
        AppTheme {
            MainScreen()
        }
    }

}