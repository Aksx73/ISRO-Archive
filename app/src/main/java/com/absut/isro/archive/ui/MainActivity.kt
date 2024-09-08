package com.absut.isro.archive.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.absut.isro.archive.R
import com.absut.isro.archive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ISROViewModel by viewModels()

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

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
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
                            onClick = { /* Handle settings click */ },
                            text = { Text(text = "About") }
                        )
                    }
                },
                    navigationIcon = {
                        //  if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back navigation"
                            )
                        }
                        // }
                    }
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
                composable("center") {
                    CentersScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }

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