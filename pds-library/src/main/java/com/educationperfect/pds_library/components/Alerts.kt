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

/**
 * EP EDS EpAlertDialog. A custom [AlertDialog] which has one confirmation action and a cancel action.
 *
 * @param openDialog Defines if the dialog will open
 * @param closeDialog Executes when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * This is not called when the dismiss button is clicked.
 * @param title Text to be displayed as the title of the Dialog.
 * @param description Text to be displayed as the description of the Dialog
 * @param textAlign The alignment of the text within the lines of the paragraph. See [TextStyle.textAlign].
 * @param confirmText Text to be displayed on the confirm choice.
 * @param confirmAction The callback to be invoked when this confirmationAction is clicked.
 * @param withDismiss Defines if the dialog will have a dismiss text button.
 * @param dismissText Text to be displayed upon tapping the dismiss choice.
 *
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

/**
 * EP EDS EpCustomDialog
 *
 * @param openDialog Defines if the dialog will open
 * @param closeDialog Executes when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * This is not called when the dismiss button is clicked.
 * @param title Text to be displayed as the title of the Dialog.
 * @param content Any composable can be inputed here as the content of the dialog.
 * @param description Text to be displayed as the description of the Dialog
 * @param textAlign The alignment of the text within the lines of the paragraph. See [TextStyle.textAlign].
 * @param confirmText Text to be displayed on the confirm choice.
 * @param confirmAction The callback to be invoked when this confirmationAction is clicked.
 * @param withDismiss Defines if the dialog will have a dismiss text button.
 * @param dismissText Text to be displayed upon tapping the dismiss choice.
 * @param confirmButtonTag Tag / ID which is used to locate dialog's confirmation text button (used for tests).
 * @param dismissButtonTag Tag / ID which is used to locate dialog's dismiss text button (used for tests).
 * @param epCustomDialogTag Tag / ID which is used to locate dialog's dismiss icon (used for tests).
 */
@Composable
private fun EPCustomDialog(
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