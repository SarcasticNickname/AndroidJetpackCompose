package com.study.variablesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val getFullTime = {
                val calendar = Calendar.getInstance()
                val hours = calendar.get(Calendar.HOUR_OF_DAY)
                val minutes = calendar.get(Calendar.MINUTE)
                val seconds = calendar.get(Calendar.SECOND)
                val milli = calendar.get(Calendar.MILLISECOND)
                "$hours:$minutes:$seconds:$milli"
            }

            val hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val currTicks = listOf("First", "Second", "Third")
            Column {
                for (tick in currTicks) {
                    if (hours < 20) {
                        Text(text = tick + " " + getFullTime() /*getTime()*/, fontSize = 24.sp)
                    } else {
                        Text(text = tick + " " + getTime(), fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

fun getTime(): String {
    val calendar = Calendar.getInstance()
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)
    return "$hours:$minutes"
}
