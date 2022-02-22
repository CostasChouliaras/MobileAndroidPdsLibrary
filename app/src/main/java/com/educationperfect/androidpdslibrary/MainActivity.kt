package com.educationperfect.androidpdslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.educationperfect.androidpdslibrary.ui.theme.AndroidPdsLibraryTheme
import com.educationperfect.pds_library.components.Avatar
import com.educationperfect.pds_library.ui.Neutral300


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPdsLibraryTheme {
                // A surface container using the 'background' color from the theme
                Icon(
                    imageVector = Icons.Rounded.ArrowForwardIos,
                    contentDescription = null,
                    tint = Neutral300,
                    modifier = Modifier.width(16.dp)
                )
                Avatar(initials = "KP", isOnline = true, height = 100.dp, width = 100.dp)
            }
        }
    }
}
