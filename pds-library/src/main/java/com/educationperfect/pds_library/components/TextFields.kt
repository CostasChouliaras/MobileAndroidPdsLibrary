package com.educationperfect.pds_library.components

/**
 * Created by George Sylaios on 13/6/21.
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.Blue500
import com.educationperfect.pds_library.ui.Neutral200
import com.educationperfect.pds_library.ui.Neutral300
import com.educationperfect.pds_library.ui.Red500

/**
 * Created by George Sylaios on 2/6/21.
 */

@Composable
fun OutlinedTextFieldWithFixedLabel(
    value: String,
    onTextChanged: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.h4,
    label: @Composable (() -> Unit)? = null,
    hasError: Boolean = false,
    errorMessage: String = "",
    hasCaption: Boolean = false,
    captionMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Neutral300,
        unfocusedBorderColor = Neutral300,
        disabledBorderColor = Neutral300,
        textColor = MaterialTheme.colors.primary,
        errorBorderColor = Red500,
        errorLabelColor = Red500
    ),
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    labelText: String,
    labelColor: Color = MaterialTheme.colors.primary,
    labelAlign: TextAlign = TextAlign.Start,
    labelStyle: TextStyle = MaterialTheme.typography.subtitle2,
    shape: Shape = MaterialTheme.shapes.small,
    modifier: Modifier,
    textFieldId: String = "",
    errorStateLabelID : String = ""
) {
    val showCaption = !hasError && hasCaption
    val backgroundColor = if (enabled) Color.Transparent else Neutral200

    Column(modifier = modifier) {
        Text(
            text = labelText,
            textAlign = labelAlign,
            style = labelStyle,
            color = labelColor,
            modifier = Modifier.testTag(labelText) //added to avoid conflict between the text of elements
        )

        OutlinedTextField(
            value = value,
            onValueChange = onTextChanged,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = hasError,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            maxLines = maxLines,
            colors = colors,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = shape,
            modifier = modifier
                .padding(top = 6.dp)
                .semantics {
                    if (hasError) error(errorMessage)
                }
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .testTag(textFieldId)
        )

        if (hasError) {
            Text(
                text = errorMessage,
                textAlign = labelAlign,
                style = MaterialTheme.typography.caption,
                color = Red500,
                modifier = Modifier.padding(top = 4.dp).testTag(errorStateLabelID)
            )
        }
        if (showCaption) {
            Text(
                text = captionMessage,
                textAlign = labelAlign,
                style = MaterialTheme.typography.caption,
                color = labelColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun PlainTextFieldWithFixedLabel(
    value: String,
    onTextChanged: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.h4,
    label: @Composable (() -> Unit)? = null,
    hasError: Boolean = false,
    errorMessage: String = "",
    hasCaption: Boolean = false,
    captionMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = if (enabled) Color.White else Neutral200,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        textColor = MaterialTheme.colors.primary,
        cursorColor = Blue500,
        errorLabelColor = Red500
    ),
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    startPadding: Dp = 12.dp,
    dividerColor: Color = Neutral300,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    withLabel: Boolean = true,
    labelText: String = "",
    labelColor: Color = MaterialTheme.colors.primary,
    labelAlign: TextAlign = TextAlign.Start,
    labelStyle: TextStyle = MaterialTheme.typography.subtitle2,
    modifier: Modifier,
    textFieldId: String = "",
    errorStateTextfieldId: String = ""
) {
    val showCaption = !hasError && hasCaption

    Column(modifier = modifier) {
        if (withLabel) {
            Text(
                modifier = Modifier.padding(start = startPadding),
                text = labelText,
                textAlign = labelAlign,
                style = labelStyle,
                color = labelColor
            )
            Spacer(modifier = Modifier.height(6.dp))
        }

        Divider(
            modifier = Modifier.height(1.dp),
            color = dividerColor
        )

        TextField(
            value = value,
            onValueChange = onTextChanged,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = hasError,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            maxLines = maxLines,
            colors = colors,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier
                .fillMaxWidth()
                .semantics {
                    if (hasError) error(errorMessage)
                }
                .testTag(textFieldId)
        )

        Divider(
            modifier = Modifier.height(1.dp),
            color = dividerColor
        )

        if (hasError) {
            Text(
                text = errorMessage,
                textAlign = labelAlign,
                style = MaterialTheme.typography.caption,
                color = Red500,
                modifier = Modifier.padding(top = 4.dp, start = startPadding).testTag(errorStateTextfieldId)
            )
        }
        if (showCaption) {
            Text(
                text = captionMessage,
                textAlign = labelAlign,
                style = MaterialTheme.typography.caption,
                color = labelColor,
                modifier = Modifier.padding(top = 4.dp, start = startPadding)
            )
        }
    }
}