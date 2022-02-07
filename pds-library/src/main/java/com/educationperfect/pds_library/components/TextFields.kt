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

/**
 * EP EDS OutlinedTextFieldWithFixedLabel. A custom input outlined text field which can also include many additional attributes, elements and/or composables.
 *
 * Outlined text fields have less visual emphasis than filled text fields. When they appear in places like forms,
 * where many text fields are placed together, their reduced emphasis helps simplify the layout.
 *
 * @param value the input text to be shown in the text field.
 * @param onTextChanged the callback that is triggered when the input service updates the text. An updated text comes as a parameter of the callback.
 * @param enabled controls the enabled state of the outlined text field. When false, the text field will be neither editable nor focusable, the input of the text field will not be selectable, visually text field will appear in the disabled UI state.
 * @param readOnly controls the editable state of the outlined text field. When true, the text field can not be modified, however, a user can focus it and copy text from it. Read-only text fields are usually used to display pre-filled forms that user can not edit.
 * @param textStyle the style to be applied to the input text. The default [textStyle] uses the LocalTextStyle defined by the theme.
 * @param label the optional [label] to be displayed inside the text field container. The default text style for internal Text is [Typography.caption] when the text field is in focus and [Typography.subtitle1] when the text field is not in focus.
 * @param hasError indicates if the text field's current [value] is in error. If set to true, the [label], bottom indicator and trailing icon by default will be displayed in error color.
 * @param errorMessage the given String [value] will appear when the outlined text field's error state is triggered.
 * @param hasCaption indicates if the text field has a caption message or not.
 * @param captionMessage the given String [value] will appear as a caption for the outlined text field.
 * @param visualTransformation transforms the visual representation of the input [value].
 * @param colors TextFieldColors that will be used to resolve color of the text and content (including [label], [placeholder], leading and trailing icons, border) for this text field in different states. See [TextFieldDefaults.outlinedTextFieldColors].
 * @param placeholder the optional [placeholder] to be displayed when the text field is in focus and the input text is empty. The default text style for internal Text is [Typography.subtitle1].
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the text field container.
 * @param trailingIcon the optional trailing icon to be displayed at the end of the text field container.
 * @param singleLine when set to true, this text field becomes a single horizontally scrolling text field instead of wrapping onto multiple lines. The keyboard will be informed to not show the return key as the [ImeAction]. Note that maxLines parameter will be ignored as the maxLines attribute will be automatically set to 1.
 * @param maxLines the maximum height in terms of maximum number of visible lines. Should be equal or greater than 1. Note that this parameter will be ignored and instead [maxLines] will be set to 1 if [singleLine] is set to true [KeyboardOptions.imeAction] field.
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardType] and [ImeAction]
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what you specified in [KeyboardOptions.imeAction].
 * @param labelText the given String [value] will appear as a [placeholder] text for the outlined text field.
 * @param labelColor the given Color will be applied as the [label]'s text color.
 * @param labelAlign the given Align will be applied as the [label]'s text align.
 * @param labelStyle the given Style will be applied as the [label]'s text style.
 * @param shape the shape of the text field's border.
 * @param modifier a [Modifier] to be applied to the outlined text field.
 * @param textFieldId Tag / ID which is used to locate the text field (used for tests).
 * @param errorStateLabelID Tag / ID which is used to locate the text field's error state [label] (used for tests).
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

/**
 * EP EDS PlainTextFieldWithFixedLabel. A custom input text field which can also include many additional attributes, elements and/or composables.
 *
 * @param value the input text to be shown in the text field.
 * @param onTextChanged the callback that is triggered when the input service updates the text. An updated text comes as a parameter of the callback.
 * @param enabled controls the [enabled] state of the plain text field. When false, the text field will be neither editable nor focusable, the input of the text field will not be selectable, visually text field will appear in the disabled UI state.
 * @param readOnly controls the editable state of the plain text field. When true, the text field can not be modified, however, a user can focus it and copy text from it. Read-only text fields are usually used to display pre-filled forms that user can not edit.
 * @param textStyle the style to be applied to the input text. The default [textStyle] uses the LocalTextStyle defined by the theme.
 * @param label the optional [label] to be displayed inside the text field container. The default text style for internal Text is [Typography.caption] when the text field is in focus and Typography.subtitle1 when the text field is not in focus.
 * @param hasError indicates if the text field's current [value] is in error. If set to true, the [label] , bottom indicator and trailing icon by default will be displayed in error color.
 * @param errorMessage the given String [value] will appear when the plain text field's error state is triggered.
 * @param hasCaption indicates if the text field has a caption message or not.
 * @param captionMessage the given String [value] will appear as a caption for the plain text field.
 * @param visualTransformation transforms the visual representation of the input [value].
 * @param colors TextFieldColors that will be used to resolve color of the text and content (including [label] , [placeholder], leading and trailing icons, border) for this text field in different states. See [TextFieldDefaults.outlinedTextFieldColors].
 * @param placeholder the optional [placeholder] to be displayed when the text field is in focus and the input text is empty. The default text style for internal Text is [Typography.subtitle1].
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the text field container.
 * @param trailingIcon the optional trailing icon to be displayed at the end of the text field container.
 * @param singleLine when set to true, this text field becomes a single horizontally scrolling text field instead of wrapping onto multiple lines. The keyboard will be informed to not show the return key as the [ImeAction]. Note that maxLines parameter will be ignored as the [maxLines] attribute will be automatically set to 1.
 * @param maxLines the maximum height in terms of maximum number of visible lines. Should be equal or greater than 1. Note that this parameter will be ignored and instead [maxLines] will be set to 1 if [singleLine] is set to true [KeyboardOptions.imeAction] field.
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what you specified in [KeyboardOptions.imeAction].
 * @param withLabel indicates if the plain text field will include a [label] .
 * @param labelText the given String [value] will appear as a [placeholder] text for the outlined text field.
 * @param labelColor the given Color will be applied as the [label] 's text color.
 * @param labelAlign the given Align will be applied as the [label] 's text align.
 * @param labelStyle the given Style will be applied as the [label] 's text style.
 * @param modifier a [Modifier] to be applied to the outlined text field.
 * @param textFieldId Tag / ID which is used to locate the text field (used for tests).
 * @param errorStateTextfieldId Tag / ID which is used to locate the text field's error state [label] (used for tests).
*/

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