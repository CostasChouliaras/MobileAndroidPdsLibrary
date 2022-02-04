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

/**
 * EP EDS activity button.
 *
 * @param text The text value that will be displayed on the button.
 * @param onCLick The callback to be invoked when this button is clicked.
 * @param loading Defines if the button will obtain a loading state when it is clicked.
 * @param enabled Controls the enabled state of the button. When false, this button will not be clickable.
 * @param colors The given value will be considered as the color of the button.
 * @param shape Defines the button's shape as well as its shadow.
 * @param modifier Modifier to be applied to the button.
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