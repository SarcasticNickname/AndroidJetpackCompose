package com.study.composablemodifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myColor = Color(red = 0xF1, green = 0xAA, blue = 0x55, alpha = 0xFF)
            Text(
                "Hello from my modifiers project",
                fontSize = 28.sp,
                modifier = Modifier
                    .background(myColor)
                    .padding(30.dp)
            )
        }
    }
}
