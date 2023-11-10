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
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
            ,horizontalArrangement = Arrangement.End
            ,verticalAlignment = Alignment.CenterVertically
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
            HabitCard(habit_name = "DJ", background_color = Color.Yellow)


        }
    }
}

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



@Composable
fun LastFiveDaysOfWeek() {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        ,horizontalArrangement = Arrangement.End
    ) {
        for (i in 1..5){
            val date = dateFormat.format(calendar.time)
            val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())

            Column(modifier = Modifier.padding(end = 5.dp))
            {
                Text("${dayOfWeek}")
                Text(text=date)
            }
            calendar.add(Calendar.DAY_OF_WEEK, -1)
        }
    }
}

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

            Text(text = "50%", fontSize = 18.sp)
            Row(modifier = Modifier
                .fillMaxWidth()
//                .padding(horizontal = 10.dp)
                ,horizontalArrangement = Arrangement.End
            ) {
                TickIconButton()
                TickIconButton()
                TickIconButton()
                TickIconButton()
                TickIconButton()
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

@Preview
@Composable
fun PreviewHomeScreenCard() {
    HomeScreen()
}


