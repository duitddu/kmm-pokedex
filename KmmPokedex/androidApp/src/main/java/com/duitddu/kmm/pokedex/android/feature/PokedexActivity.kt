package com.duitddu.kmm.pokedex.android.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duitddu.kmm.pokedex.android.feature.detail.PokemonDetailScreen
import com.duitddu.kmm.pokedex.android.feature.list.PokemonListScreen
import com.duitddu.kmm.pokedex.android.feature.list.PokemonListViewModel
import com.duitddu.kmm.pokedex.android.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            PokedexTheme {
                PokedexNavHost(navController)
            }
        }
    }
}

@Composable
fun PokedexNavHost(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = "list"
    ) {
        composable("list") {
            PokemonListScreen(hiltViewModel()) {
                val route = "detail/${it.name}"
                navController.navigate(route)
            }
        }
        composable(
            "detail/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            )
        ) {
            PokemonDetailScreen(hiltViewModel()) {
                navController.navigateUp()
            }
        }
    }
}
