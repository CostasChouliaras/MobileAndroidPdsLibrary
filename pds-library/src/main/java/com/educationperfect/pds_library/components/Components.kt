package com.educationperfect.pds_library.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.educationperfect.pds_library.R
import com.educationperfect.pds_library.ui.Neutral300
import com.educationperfect.pds_library.ui.Neutral400

/**
 * Created by george on 22/06/2021
 */

/**
 * EP EDS EpIconActionListItem
 *
 * @param label The text to be displayed.
 * @param labelColor Defines the color of the text.
 * @param labelStyle Style configuration for the text such as color, font, line height etc.
 * @param icon the given icon will be displayed at the beginning of the list item.
 * @param iconTint Tint to be applied to the icon. If [Color.Unspecified] is provided, then no tint is applied
 * @param iconSize the given value will be considered as the size of the icon.
 * @param iconContentDescription Text used by accessibility services to describe what this icon represents.
 * This should always be provided unless this icon is used for decorative purposes, and does not represent a meaningful action
 * that a user can take. This text should be localized, such as by using [androidx.compose.ui.res.stringResource] or similar.
 * @param withTopDivider Defines if the list item will include a top [Divider].
 * @param topDividerPadding Defines if the list item will include top [Divider] [padding].
 * @param withBottomDivider Defines if the list item will include a bottom [Divider].
 * @param bottomDividerPadding Defines if the list item will include bottom [Divider] [padding].
 * @param dividerThickness Defines the thickness of the [Divider].
 * @param horizontalPadding Apply horizontal dp space along the left and right edges of the content,
 * and vertical dp space along the top and bottom edges. [Padding] is applied before content measurement and takes precedence;
 * content may only be as large as the remaining space. Negative padding is not permitted — it will cause [IllegalArgumentException]. See [Modifier.offset].
 * @param verticalPadding Apply vertical dp space along the left and right edges of the content,
 * and vertical dp space along the top and bottom edges. Padding is applied before content measurement and takes precedence;
 * content may only be as large as the remaining space. Negative padding is not permitted — it will cause IllegalArgumentException. See Modifier.offset.
 * @param withNavigationIcon Defines if the list item will include a navigation icon.
 * @param subtext The given value will be added as a subtext to the list item.
 * @param onClick The callback to be invoked when this list item is clicked.
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

/**
 * EP EDS EpBottomBarWithActionButton
 *
 * @param text The text to be displayed.
 * @param loading Defines if the button will obtain a loading state when it is clicked.
 * @param enabled  Controls the enabled state of the bottom bar. When false, this button will not be clickable.
 * @param action The callback to be invoked when this button is clicked.
 */

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

/**
 * EP EDS ProfileHeader. A [Avatar] EP EDS custom composable which is followed by text information.
 *
 * @param initials The given 2 character String will be provided as the initials of that composable
 * @param name The text to be displayed as the [name] of the profile header.
 * @param title The text to be displayed as the title of the profile header.
 * @param alpha The fraction of children's alpha value and must be between 0 and 1, inclusive
 * @param spacerHeight Declare the preferred height of the content to be exactly [height] dp.
 */

@Composable
fun ProfileHeader(
    initials: String,
    name: String,
    title: String,
    isOnline: Boolean,
    alpha: Float,
    spacerHeight: Dp = 48.dp
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .testTag("teacherInfo")
            .alpha(alpha),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(spacerHeight))

        Avatar(initials = initials, isOnline = isOnline, width = 56.dp, height = 56.dp)

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