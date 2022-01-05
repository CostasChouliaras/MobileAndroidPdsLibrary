package com.educationperfect.pds_library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.Blue500
import com.educationperfect.pds_library.ui.Green500
import com.educationperfect.pds_library.ui.Neutral900
import com.educationperfect.pds_library.ui.Red500

/**
 * Created by george on 06/10/2021
 */

enum class EdsSnackBarType(val textColor: Color, val backgroundColor: Color, val icon: ImageVector) {
    Info(Color.White, Blue500, Icons.Outlined.Info),
    Error(Color.White, Red500, Icons.Rounded.Warning),
    Success(Color.White, Green500, Icons.Rounded.CheckCircle),
    Passive(Color.White, Neutral900, Icons.Outlined.Info)
}

@Composable
fun EpSnackBar(
    text: String,
    withIcon: Boolean = true,
    withAction: Boolean = true,
    snackBarType: EdsSnackBarType,
    onClose: () -> Unit,
    epSnackBarDismissIconTAG: String = ""
) {
    Snackbar(
        shape = MaterialTheme.shapes.small,
        backgroundColor = snackBarType.backgroundColor,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp),
        elevation = 8.dp,
        action = {
            if (withAction) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Rounded.Close, null, tint = snackBarType.textColor, modifier = Modifier.testTag(epSnackBarDismissIconTAG))
                    }
                }
            }
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val padding = if (withIcon) 12.dp else 0.dp
            if (withIcon) {
                Icon(snackBarType.icon, null, tint = snackBarType.textColor)
            }
            Text(
                text = text,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6,
                color = snackBarType.textColor,
                modifier = Modifier.padding(start = padding)
            )
        }
    }
}