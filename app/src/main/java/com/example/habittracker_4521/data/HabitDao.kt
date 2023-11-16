package com.example.habittracker_4521.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg habits: Habit)

    @Update
    fun update(habit: Habit)

    @Delete
    fun delete(habit: Habit)

    @Query("SELECT * FROM habit_table")
    fun getHabits(): List<Habit>

}