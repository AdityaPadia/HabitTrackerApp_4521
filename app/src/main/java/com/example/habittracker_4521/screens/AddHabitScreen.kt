package com.example.habittracker_4521.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker_4521.addHabitInDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen() {


    var nameInput by remember { mutableStateOf("") }
    var motivationInput by remember { mutableStateOf("") }
    val repeatOptions = listOf("Every day", "Once per week", "3 times per week", "5 times per week")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Create a Habit", color = Color.Black, fontSize = 20.sp)
        }

        //Habit Name : TextInput Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)
                .height(70.dp)
                .padding(10.dp),
        ) {
            TextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text("NAME")},
                placeholder = { Text(text = "Read a book, Meditate etc.") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Cyan),
                modifier = Modifier
                    .fillMaxSize(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                maxLines = 1,
                singleLine = true
            )
        }

        //Motivation Input TextInput Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .height(80.dp)
                .padding(10.dp),
        ) {
            OutlinedTextField(
                value = motivationInput,
                onValueChange = { motivationInput = it },
                label = { Text("MOTIVATE YOURSELF")},
                placeholder = { Text(text = "Let's do this!") },
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxSize(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 13.sp
                ),
                maxLines = 1,
                singleLine = true
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .width(150.dp)
                    .fillMaxHeight()
                ,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Repeat", fontSize = 18.sp)
            }
            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
            ) {
                dropDownMenu()
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(80.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //TODO : Connect Button with Room DB
            Button(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Button Icon", modifier = Modifier.size(20.dp))
                Text(text = "Add Habit", Modifier.padding(start = 10.dp))
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenu() {
    val repeatOptions = listOf("Every day", "Once per week", "3 times per week", "5 times per week")
    val contextForToast = LocalContext.current.applicationContext

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember {
        mutableStateOf(repeatOptions[0])
    }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()

        ,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        )
    {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            repeatOptions.forEach { selectedOption ->
                DropdownMenuItem(
                    text = {},
                    onClick = {
                        selectedItem = selectedOption
                        Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                        expanded = false
                    })
                Text(text = selectedOption)
            }
        }
    }
}