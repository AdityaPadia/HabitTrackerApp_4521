package com.example.habittracker_4521.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker_4521.data.Habit
import com.example.habittracker_4521.data.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val habitRepository: HabitRepository) : ViewModel() {
    val habitList: LiveData<List<Habit>> = habitRepository.allHabits

    val foundHabit: LiveData<Habit> = habitRepository.foundHabit

    fun getAllHabits() {
        habitRepository.getAllHabits()
    }

    fun addHabit(habit : Habit) {
        habitRepository.addHabit(habit)
        getAllHabits()
    }

    fun updateHabitDetails(habit : Habit) {
        habitRepository.updateHabitDetails(habit)
        getAllHabits()
    }

}