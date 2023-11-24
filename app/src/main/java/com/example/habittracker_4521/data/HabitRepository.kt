package com.example.habittracker_4521.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitRepository (private val habitDao: HabitDao){

    var allHabits = MutableLiveData<List<Habit>>()
    val foundHabit = MutableLiveData<Habit>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addHabit(newHabit: Habit){
        coroutineScope.launch(Dispatchers.IO) {
            habitDao.insertAll(newHabit)
        }
    }

    fun updateHabitDetails(newHabit: Habit){
        coroutineScope.launch(Dispatchers.IO) {
            habitDao.update(newHabit)
        }
    }

    fun getAllHabits() {
        coroutineScope.launch(Dispatchers.IO) {
            habitDao.getHabits()
        }
    }
}