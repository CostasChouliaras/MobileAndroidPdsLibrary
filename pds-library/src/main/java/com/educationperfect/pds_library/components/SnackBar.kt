package com.educationperfect.pds_library.components

import androidx.compose.foundation.layout.*
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

/**
 * EP EDS EpSnackBar. Custom EP snack bar. Snack bars provide brief messages about app processes at the bottom of the screen.
 * Snack bars inform users of a process that an app has performed or will perform. They appear temporarily, towards the bottom of the screen.
 * They shouldn’t interrupt the user experience, and they don’t require user input to disappear.
 * Snack bar can contain a single action. Because Snack bar disappears automatically, the action shouldn't be "Dismiss" or "Cancel".
 *
 * @param text The text value that will be displayed on Snack bar.
 * @param withIcon defines if the snack bar will include an icon.
 * @param withAction defines if the snack bar will have an action when is tapped.
 * @param snackBarType the Avatar composable size is depended on the type of the Avatar.
 * @param onClose action that will be triggered upon tapping close icon.
 * @param epSnackBarDismissIconTAG Tag / ID which is used to snack bar's dismiss icon (used for tests).
 */

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
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 12.dp, vertical = 12.dp),
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