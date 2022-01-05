package com.educationperfect.pds_library.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.Neutral1000

/**
 * Created by George Sylaios on 2/6/21.
 */

@Composable
fun EpAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    title: String,
    description: String,
    textAlign: TextAlign? = TextAlign.Start,
    confirmText: String = "Confirm",
    confirmAction: () -> Unit,
    withDismiss: Boolean = true,
    dismissText: String = "Dismiss"
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h3,
                    color = Neutral1000,
                    textAlign = textAlign
                )
            },
            text = {
                Text(
                    text = description,
                    style = MaterialTheme.typography.subtitle2,
                    color = Neutral1000,
                    textAlign = textAlign
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        confirmAction()
                    },
                    modifier = Modifier.testTag("confirmTag")
                ) {
                    Text(
                        text = confirmText,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            },
            modifier = Modifier.padding(24.dp),
            dismissButton = {
                if (withDismiss) {
                    TextButton(
                        onClick = closeDialog,
                        modifier = Modifier.testTag("dismissTag")
                    )
                    {
                        Text(
                            text = dismissText,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun EPCustomDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    title: String,
    content: @Composable () -> Unit,
    confirmText: String = "Confirm",
    confirmAction: () -> Unit,
    withDismiss: Boolean = true,
    dismissText: String = "Dismiss",
    confirmButtonTag : String,
    dismissButtonTag: String,
    epCustomDialogTag : String
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = title,
                    color = Neutral1000,
                    style = MaterialTheme.typography.h3
                )
            },
            text = {
                content()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        confirmAction()
                    },
                    modifier = Modifier.testTag(confirmButtonTag)
                ) {
                    Text(
                        text = confirmText,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            },
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 24.dp)
                .fillMaxWidth()
                .testTag(epCustomDialogTag),
            dismissButton = {
                if (withDismiss) {
                    TextButton(
                        onClick = closeDialog,
                        modifier = Modifier.testTag(dismissButtonTag)
                    ) {
                        Text(
                            text = dismissText,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        )
    }
}