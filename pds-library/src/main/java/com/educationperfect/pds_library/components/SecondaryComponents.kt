package com.educationperfect.pds_library.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import org.jetbrains.annotations.NotNull

@Composable
fun EpClickableText(
    annotatedText: AnnotatedString,
    tag: String,
    action: (String) -> Unit
) {
    ClickableText(text = annotatedText) { offset ->
        annotatedText.getStringAnnotations(tag = tag, start = offset, end = offset)
            .firstOrNull()?.let { annotation ->
                action(annotation.item)
            }
    }
}

@Composable
fun <T>ContentWithModelError(
    model: T?,
    content: @Composable (T) -> Unit
) {
    when (model) {
        is NotNull -> content(model)
        else -> EpErrorScreen()
    }
}