package com.example.habittracker_4521.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.habittracker_4521.data.Habit
import com.example.habittracker_4521.data.HabitDatabase
import com.example.habittracker_4521.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*


// This is the main function of the screen
// Contains the entire layout of the Home Screen of the application



@Composable
public fun HomeScreen() {

    var habit by remember { mutableStateOf(Habit(habitName = null, complete = null)) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        isLoading = true
        //Get Data
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Add Habit")
            AddButton(onClick = {Log.d("AddButton", "ClickedAddButton!")})
        }

        Row {
            Text(text = "Habit", fontSize = 30.sp)
            LastFiveDaysOfWeek()
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {

            HabitCard(habit_name = "Read", background_color = Color.Green)
            HabitCard(habit_name = "Meditate", background_color = Color.LightGray)
            HabitCard(habit_name = "Play Chess", background_color = Color.Cyan)
            HabitCard(habit_name = "Journal", background_color = Color.Blue)
//            HabitCard(habit_name = "DJ", background_color = Color.Yellow)
        }
    }
}

//Button to add a Habit to the list of habits
@Composable
fun AddButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
    ) {
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "Add",
                tint = Color.Black,
            )
        }
    }
}


//This is the UI component to get the previous 5 days of the week
@Composable
fun LastFiveDaysOfWeek() {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    val dayOfWeekArray : MutableList<String> = ArrayList()
    val datesArray : MutableList<String> = ArrayList()

    for (i in 1 .. 5) {
        val date = dateFormat.format(calendar.time)
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())

        dayOfWeekArray.add(dayOfWeek.toString())
        datesArray.add(date.toString())


        calendar.add(Calendar.DAY_OF_WEEK, -1)
    }



    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        ,horizontalArrangement = Arrangement.End
    ) {
        for (i in 0 .. 4){

            Column(modifier = Modifier.padding(end = 5.dp))
            {
                Text(dayOfWeekArray[4-i])
                Text(text=datesArray[4-i])
            }
            calendar.add(Calendar.DAY_OF_WEEK, -1)
        }
    }
}

//This button is the icon button inside each habit
//TODO : Need to connect to backend functionality for different days of the week
@Composable
fun TickIconButton()
{
//    val contextForToast = LocalContext.current.applicationContext
    var isChecked by remember { mutableStateOf(false) }

    IconButton(onClick = {
        isChecked = !isChecked
//        Toast.makeText(contextForToast, "Click!", Toast.LENGTH_SHORT).show()
    }) {
        val icon = if (isChecked) {
            Icons.Filled.CheckCircle
        } else {
            Icons.Filled.AddCircle
        }
        Icon(
            imageVector = icon,
            contentDescription = ""
        )
    }
}

//This is the UI component of the habit card
//TODO : Need to figure out the % calculation and movement of ticks dynamically
@Composable
fun HabitCard(habit_name: String, background_color: Color, onClick: () -> Unit = {
    Log.d("ClickableComposable", "Clicked!")
})
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = background_color)


    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

        ) {

            Text(text = "%", fontSize = 18.sp)
            Row(modifier = Modifier
                .fillMaxWidth()
//                .padding(horizontal = 10.dp)
                ,horizontalArrangement = Arrangement.End
            ) {
                //5 tick buttons for last 5 day habits
                for (i in 1..5){
                    TickIconButton()
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

        ) {
            Text(text = "$habit_name", fontSize = 25.sp)
        }
    }

}


