package com.example.habittracker_4521.navigation



import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import com.example.habittracker_4521.screens.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.habittracker_4521.screens.CalendarScreen

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.fillMaxSize()

    ) {
        composable(Routes.Home.route) {
            HomeScreen()
        }
        composable(Routes.Calendar.route) {
            CalendarScreen()
        }
    }
}

@Composable
fun BottomNavigation(
    routes: List<Routes>,
    onNavigateToDestination: (route: String) -> Unit,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route as NavDestination

    NavigationBar(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
            .height(70.dp)
    ) {
        routes.forEach{route ->
            val selected = currentRoute?.hierarchy?.any { it.route == route.route} == true
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(route.route) },
                icon = {
                    val icon = if (selected) {
                        //TODO : Need to update Icon Logic
                        Icons.Rounded.Home
                    } else {
                        //TODO : Need to update Icon Logic
                        Icons.Rounded.Home
                    }
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.size(16.dp),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        //TODO : Need to update Icon Text
                        text = "Home"
                    )
                }
            )

        }
    }
}