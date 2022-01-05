package com.educationperfect.pds_library.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.Neutral400
import com.educationperfect.pds_library.ui.Neutral700

/**
 * Created by George Sylaios on 2/6/21.
 */

@Composable
fun EpActivityButton(
    text: String,
    onCLick: () -> Unit,
    loading: Boolean = false,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        disabledBackgroundColor = Neutral400,
        contentColor = Color.White,
        disabledContentColor = Neutral700
    ),
    shape: Shape = MaterialTheme.shapes.medium,
    modifier: Modifier
) {
    Button(
        colors = colors,
        onClick = onCLick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = shape
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.White,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                } else {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}