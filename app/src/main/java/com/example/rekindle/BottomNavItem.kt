package com.example.rekindle

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class BottomNavItem(val title: String, var icon: Int, var route: String) {
    object Home : BottomNavItem(
        "Home",
        icon = R.drawable.ic_baseline_home_24,
        route = "home"
    )

    object Settings : BottomNavItem(
        "Settings",
        icon = R.drawable.ic_baseline_settings_24,
        route = "settings"
    )
}

const val movie_id = "movie_id"
const val movie_detail_route = "movie_detail"
val movieDetailRouteArgs = "${movie_detail_route}/{${movie_id}}"

val movie_detail_arguments = listOf(
    navArgument(movie_id) { type = NavType.StringType }
)