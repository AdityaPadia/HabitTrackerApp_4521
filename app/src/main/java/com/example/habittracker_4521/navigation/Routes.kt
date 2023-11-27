package com.example.habittracker_4521.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Routes(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    object Home : Routes(
        route = "Home",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Calendar: Routes(
        route = "Calendar",
        title = "Calendar",
        icon = Icons.Outlined.DateRange
        )
    object AddHabit: Routes(
        route = "AddHabit",
        title = "Add Habit",
        icon = Icons.Outlined.Add
    )

}
