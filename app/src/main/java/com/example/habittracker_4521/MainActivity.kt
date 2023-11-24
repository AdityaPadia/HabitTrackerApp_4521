package com.example.habittracker_4521


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker_4521.data.Habit
import com.example.habittracker_4521.data.HabitDao
import com.example.habittracker_4521.data.HabitDatabase
import com.example.habittracker_4521.data.HabitRepository
import com.example.habittracker_4521.navigation.BottomNavigation
import com.example.habittracker_4521.navigation.Navigation
import com.example.habittracker_4521.navigation.Routes
import com.example.habittracker_4521.screens.AddHabitScreen
import com.example.habittracker_4521.screens.CalendarScreen
import com.example.habittracker_4521.screens.HomeScreen
import com.example.habittracker_4521.viewmodel.HomeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val readHabit: Habit = Habit( habitName ="Read", complete = false)
        val meditateHabit: Habit = Habit( habitName ="Read", complete = false)
        val exerciseHabit: Habit = Habit( habitName ="Read", complete = false)

//        val habitDb = Room.databaseBuilder(
//            applicationContext,
//            HabitDatabase::class.java,
//            "habit.db"
//        ).build()
//
//        habitDb.habitDAO().insertAll(readHabit, meditateHabit, exerciseHabit)

        setContent {
            AddHabitScreen()
//            val navController = rememberNavController()


//            Navigation(navController = navController)
        }
    }
}

fun addHabitInDB(
    context: Context,
    navController: NavController,
    habit: Habit,
    homeViewModel: HomeViewModel) {
    homeViewModel.addHabit(habit)
    navController.popBackStack()
}

//@Composable
//fun MainScreenView(){
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomNavigation(navController = navController) }
//    ) {
//        Navigation(navController = navController)
//    }
//}





