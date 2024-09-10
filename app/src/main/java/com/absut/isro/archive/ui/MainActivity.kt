package com.absut.isro.archive.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.fragment.app.activityViewModels
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
import com.absut.isro.archive.databinding.ActivityMainBinding
import com.absut.isro.archive.ui.screens.CentersScreen
import com.absut.isro.archive.ui.screens.HomeScreen
import com.absut.isro.archive.ui.screens.LauncherScreen
import com.absut.isro.archive.ui.screens.SatelliteScreen
import com.absut.isro.archive.ui.screens.SpacecraftScreen
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ISROViewModel by viewModels()

    private val routeToTitleMap = mapOf(
        "home" to "ISRO \uD83D\uDE80 Archive",
        "spacecraft" to "Spacecrafts",
        "launchers" to "Launchers",
        "satellite" to "Customer Satellites",
        "centre" to "Centres"
    )

    companion object{
        const val ROUTE_HOME = "home"
        //todo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        /* binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)

         setSupportActionBar(binding.toolbar)

         val navHostFragment =
             supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         val navController = navHostFragment.navController
         appBarConfiguration = AppBarConfiguration(navController.graph)
         setupActionBarWithNavController(navController, appBarConfiguration)*/

        setContent {
            AppTheme {
                MainScreen()
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
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
                        text = routeToTitleMap[currentDestination?.route] ?: stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
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
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen(navController = navController) }
                composable("spacecraft") {
                    SpacecraftScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable("satellite") {
                    SatelliteScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable("launchers") {
                    LauncherScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable("centre") {
                    CentersScreen(
                        navController = navController,
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_about -> {
                //do something
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}