package com.example.car_damage_diagnosis.ui.screens.home

import androidx.compose.foundation.layout.* // For Column, Spacer, padding, etc.
import androidx.compose.material3.* // For MaterialTheme, Button, Icon, IconButton, etc.
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource // For painterResource
import androidx.compose.ui.unit.dp // For dp unit
import com.example.car_damage_diagnosis.R // Assuming R is your resource class
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.background // For background
import androidx.compose.foundation.shape.RoundedCornerShape // For RoundedCornerShape
import androidx.compose.ui.graphics.Color // For Color
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.draw.shadow

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "History")

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 14.dp, bottom = 14.dp)
                .size(60.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = Color(0xFFECE6F0),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { /* Действие при нажатии */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = "Download Icon",
                    tint = Color.Unspecified
                )
            }
        }
    }
}