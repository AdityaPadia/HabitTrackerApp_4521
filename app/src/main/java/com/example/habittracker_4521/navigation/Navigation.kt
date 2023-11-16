package com.example.habittracker_4521.navigation

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.habittracker_4521.screens.HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}