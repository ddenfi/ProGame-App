package com.ddenfi.capstonecompose.ui.nav_graph

import androidx.annotation.DrawableRes
import com.ddenfi.capstonecompose.R

sealed class Screen(
    val route:  String,
    val tittle:String,
    @DrawableRes val icon:Int
){
    object Home: Screen(
        route = "home_screen",
        tittle = "Home",
        icon = R.drawable.ic_list
    )
    object Favorite: Screen(
        route = "favorite_screen",
        tittle = "Favorite",
        icon = R.drawable.ic_favorite_game
    )
    object About: Screen(
        route = "about_screen",
        tittle = "About Me",
        icon = R.drawable.ic_about
    )
    object DetailGame: Screen(
        route = "detail_game/{id}",
        tittle = "Detail Game",
        icon = R.drawable.ic_game
    ){
        fun createRoute(gameId:Int) = "detail_game/$gameId"
    }
}
