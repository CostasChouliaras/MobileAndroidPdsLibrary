package com.educationperfect.pds_library.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
// import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.educationperfect.pds_library.R
import com.educationperfect.pds_library.ui.Neutral300
import com.educationperfect.pds_library.ui.Neutral400
import org.jetbrains.annotations.NotNull

/**
 * Created by george on 22/06/2021
 */


@Composable
fun EpIconActionListItem(
    label: String,
    labelColor: Color = MaterialTheme.colors.primary,
    labelStyle: TextStyle = MaterialTheme.typography.h3,
    icon: Painter? = null,
    iconTint: Color = MaterialTheme.colors.primaryVariant,
    iconSize: Dp = 24.dp,
    iconContentDescription: String = "",
    withTopDivider: Boolean = true,
    topDividerPadding: Dp = 0.dp,
    withBottomDivider: Boolean = true,
    bottomDividerPadding: Dp = 0.dp,
    dividerThickness: Dp = 1.dp,
    horizontalPadding: Dp = 12.dp,
    verticalPadding: Dp = 16.dp,
    withNavigationIcon: Boolean = true,
    subtext: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {
        if (withTopDivider) {
            Divider(
                thickness = dividerThickness,
                color = Neutral300,
                modifier = Modifier.padding(start = topDividerPadding)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = verticalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    painter = icon,
                    tint = iconTint,
                    contentDescription = iconContentDescription,
                    modifier = Modifier.size(iconSize)
                )
            }
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                modifier = Modifier.weight(7F),
                text = label,
                textAlign = TextAlign.Start,
                style = labelStyle,
                color = labelColor
            )
            if (subtext != null) {
                Text(
                    modifier = Modifier.weight(14F),
                    text = subtext,
                    textAlign = TextAlign.End,
                    style = labelStyle,
                    color = Neutral400
                )
            }
            if (withNavigationIcon) {
                Icon(
                    imageVector = Icons.Rounded.ArrowForwardIos,
                    contentDescription = null,
                    tint = Neutral300,
                    modifier = Modifier.width(16.dp)
                )
            }
        }
        if (withBottomDivider) {
            Divider(
                thickness = dividerThickness,
                color = Neutral300,
                modifier = Modifier.padding(start = bottomDividerPadding)
            )
        }
    }
}

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

@Composable
fun EpErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Need error image
        Image(
            painter = painterResource(R.drawable.ic_student_redirect),
            contentDescription = null
        )
        Spacer(
            Modifier.height(31.dp)
        )
        Text(text = stringResource(R.string.error),
            style = MaterialTheme.typography.h4,
            color = colorResource(R.color.midnight_500)
        )
    }
}

@Composable
fun EpBottomBarWithActionButton(
    text: String,
    loading: Boolean,
    enabled: Boolean,
    action: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
        EpActivityButton(
            text = text,
            onCLick = action,
            loading = loading,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProfileHeader(
    initials: String,
    name: String,
    title: String,
    alpha: Float,
    spacerHeight: Dp = 48.dp
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .alpha(alpha)
            .testTag("teacherInfo"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(spacerHeight))

        Avatar(initials = initials, width = 56.dp, height = 56.dp)

        Spacer(Modifier.height(8.dp))

        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(spacerHeight))
    }
}