package com.ddenfi.capstonecompose.ui.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ddenfi.capstonecompose.ui.view.AboutScreen
import com.ddenfi.capstonecompose.ui.view.DetailGameScreen
import com.ddenfi.capstonecompose.ui.view.FavoriteScreen
import com.ddenfi.capstonecompose.ui.view.HomeScreen
import com.ddenfi.capstonecompose.ui.viewmodel.HomeViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                viewModel = viewModel,
                navToDetail = { gameId ->
                    navController.navigate(Screen.DetailGame.createRoute(gameId) )
                }
            )
        }

        composable(
            route = Screen.Favorite.route
        ) {
            FavoriteScreen(
                viewModel = viewModel,
                navToDetail = { gameId: Int ->
                    navController.navigate(Screen.DetailGame.createRoute(gameId))
                }
            )
        }

        composable(
            route = Screen.About.route
        ) {
            AboutScreen()
        }

        composable(
            route = Screen.DetailGame.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ) {
            val gameId = it.arguments?.getInt("id") ?: 0
            DetailGameScreen(gameId = gameId)
        }


    }
}