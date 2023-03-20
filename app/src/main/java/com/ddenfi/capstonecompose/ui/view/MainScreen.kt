package com.ddenfi.capstonecompose.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ddenfi.capstonecompose.ui.nav_graph.AppNavGraph
import com.ddenfi.capstonecompose.ui.nav_graph.Screen
import com.ddenfi.capstonecompose.ui.theme.PurpleButtonSelected
import com.ddenfi.capstonecompose.ui.theme.PurpleButtonUnselected

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    Scaffold(bottomBar = {
        if (currentDestination != Screen.DetailGame.route) BottomBar(
            navController = navController
        )
    }) {
        Column(
            modifier = modifier
                .padding(it),
        ) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screen.Home,
        Screen.Favorite,
        Screen.About,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.tittle,
                    )
                },
                label = {
                    Text(text = it.tittle)
                },
                selected = currentDestination?.hierarchy?.any { navDestination ->
                    navDestination.route == it.route
                } == true,
                onClick = { navController.navigate(it.route) },
                unselectedContentColor = PurpleButtonUnselected,
                selectedContentColor = PurpleButtonSelected,
                alwaysShowLabel = false,
            )
        }
    }
}