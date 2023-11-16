package com.example.habittracker_4521.screens

import android.graphics.Paint
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar


@Composable
fun CalendarScreen() {
    val calendarInputList by remember {
        mutableStateOf(createCalendarHabits())
    }

    var clickCalendarElem by remember {
        mutableStateOf<CalendarInput?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.TopCenter
        ) {
        Calendar(
            calendarInput = calendarInputList,
            onDayClick = { day ->
                clickCalendarElem = calendarInputList.first { it.day == day }
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1.3f)
            ,
            strokeWidth = 5f,
            month = "November"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.Center)
        ) {
            clickCalendarElem?.habits?.forEach {
                Text(
                    if(it.contains("Day")) it else "`$it",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if(it.contains("Day")) 25.sp else 18.sp,

                )
            }
        }
    }
}

@Composable
private fun Calendar(
    modifier: Modifier = Modifier,
    calendarInput: List<CalendarInput>,
    onDayClick:(Int) -> Unit,
    strokeWidth: Float,
    month: String,
) {
    val CALENDAR_ROWS = 5
    val CALENDAR_COLUMNS = 7

    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }

    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var animationRadius by remember {
        mutableStateOf(0f)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = month,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 30.sp,
        )
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(key1 = true) {
                detectTapGestures(
                    onTap = { offset ->
                        val column = (offset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
                        val row = (offset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

                        val day = column + (row - 1) * CALENDAR_COLUMNS

                        if (day <= calendarInput.size) {
                            onDayClick(day)
                            clickAnimationOffset = offset
                            scope.launch {
                                animate(0f, 200f, animationSpec = tween(300)) { value, _ ->
                                    animationRadius = value
                                }
                            }
                        }
                    }
                )
            })
        {
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / CALENDAR_ROWS
            val xSteps = canvasWidth / CALENDAR_COLUMNS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

            val path = Path().apply {
                moveTo((column-1)*xSteps, (row-1)*ySteps)
                lineTo(column*xSteps, (row-1)*ySteps)
                lineTo(column*xSteps, row*ySteps)
                lineTo((column-1)*xSteps, row*ySteps)
                close()
            }

            clipPath(path) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Color.Magenta.copy(0.7f), Color.Magenta.copy(0.2f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f,
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                Color.LightGray,
                cornerRadius = CornerRadius(25f, 25f),
                style = Stroke(
                    width = strokeWidth
                )
            )

            for (i in 1 until CALENDAR_ROWS){
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, ySteps * i),
                    end = Offset(canvasWidth, ySteps * i),
                    strokeWidth = strokeWidth
                )
            }

            for (i in 1 until CALENDAR_COLUMNS){
                drawLine(
                    color = Color.LightGray,
                    start = Offset(xSteps * i, 0f),
                    end = Offset(xSteps * i, canvasHeight),
                    strokeWidth = strokeWidth
                )
            }

            val textHeight = 17.dp.toPx()

            for (i in calendarInput.indices) {
                //Position of calendar text on the canvas
                val textPositionX = xSteps * (i % CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth/2

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${i+1}",
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight
                            color = Color.Black.toArgb()
                            isFakeBoldText = false
                        }
                    )
                }
            }
        }
    }
}


data class CalendarInput(
    val day: Int,
    val habits: List<String> = emptyList()
) {

}


private fun createCalendarHabits(): List<CalendarInput> {
    val calendarInputs = mutableListOf<CalendarInput>()

    val currentDate = LocalDate.now()
    val yearMonth = YearMonth.from(currentDate)
    val daysInMonth = yearMonth.lengthOfMonth()

    for (i in 1 .. daysInMonth) {
        calendarInputs.add(
            CalendarInput(
                i,
                habits = listOf(
                    "Day $i",
                    "Habit 1",
                    "Habit 2",
                )
            )
        )
    }

    return calendarInputs
}
