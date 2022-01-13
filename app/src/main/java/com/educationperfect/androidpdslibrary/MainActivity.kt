package com.educationperfect.androidpdslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.educationperfect.androidpdslibrary.ui.theme.AndroidPdsLibraryTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPdsLibraryTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}
