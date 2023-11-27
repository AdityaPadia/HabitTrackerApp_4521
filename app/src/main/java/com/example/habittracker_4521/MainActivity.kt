package com.example.habittracker_4521


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.habittracker_4521.data.Habit
import com.example.habittracker_4521.navigation.NavigationGraph
import com.example.habittracker_4521.screens.AddHabitScreen
import com.example.habittracker_4521.viewmodel.HomeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val readHabit: Habit = Habit( habitName ="Read", complete = false)
        val meditateHabit: Habit = Habit( habitName ="Meditate", complete = false)
        val exerciseHabit: Habit = Habit( habitName ="Meditate", complete = false)

//        val habitDb = Room.databaseBuilder(
//            applicationContext,
//            HabitDatabase::class.java,
//            "habit.db"
//        ).build()
//
//        habitDb.habitDAO().insertAll(readHabit, meditateHabit, exerciseHabit)

        setContent {
            val navController = rememberNavController()
            NavigationGraph(navController = navController)
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





