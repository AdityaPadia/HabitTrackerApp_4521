package com.example.habittracker_4521


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker_4521.navigation.Navigation
import com.example.habittracker_4521.screens.CalendarScreen
import com.example.habittracker_4521.screens.HomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Navigation(navController = rememberNavController())
            //Setting Content of the MainActivity to CalendarScreen.kt
//            CalendarScreen()
        }
    }
}

