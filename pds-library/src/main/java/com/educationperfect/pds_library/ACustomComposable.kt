package com.educationperfect.pds_library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ACustomComposable()
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text =  "This is a text",
            modifier = Modifier.fillMaxSize(),
            color = Color.Cyan
        )
        Button(onClick = { /*TODO*/ }) {
            Text(
                text =  "Click me",
                modifier = Modifier.fillMaxSize(),
                color = Color.Cyan
            )
        }
    }
}