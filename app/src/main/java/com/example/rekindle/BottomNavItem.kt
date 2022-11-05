package com.example.rekindle

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