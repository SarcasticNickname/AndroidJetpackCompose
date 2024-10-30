package com.study.composablemodifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
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

            // Paddings
            val padVal = PaddingValues(start = 10.dp, end = 10.dp)
            val padMod = Modifier.padding(padVal)
            val padFull = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            val padHor = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)

            // Offsets
            val offMod = Modifier.offset(x = 10.dp, y = 10.dp)


            Text(
                "Hello from my modifiers project",
                fontSize = 28.sp,
                modifier = Modifier
                    .background(myColor)
                    .padding(30.dp)
                    .then(padMod)
                    .then(padFull)
                    .then(padHor)
                    .then(offMod)
            )
        }
    }
}
