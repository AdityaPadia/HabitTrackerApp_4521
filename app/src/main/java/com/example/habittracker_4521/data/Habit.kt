package com.example.habittracker_4521.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "habit_table")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "habit_name")
    var habitName: String?,

    @ColumnInfo(name = "complete")
    var complete: Boolean?
)
