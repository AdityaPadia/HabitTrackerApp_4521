package com.example.habittracker_4521.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable



sealed class Routes(val route: String) {
    object Home : Routes(route = "Home")

    object Calendar: Routes("Calendar")

}
