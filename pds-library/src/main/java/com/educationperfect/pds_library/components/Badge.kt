package com.educationperfect.pds_library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.Neutral200
import com.educationperfect.pds_library.ui.Neutral900
import com.educationperfect.pds_library.ui.Red300
import com.educationperfect.pds_library.ui.Red500

/**
 * Created by george on 16/08/2021
 */

enum class EdsBadgeType(val textColor: Color, val backgroundColor: Color, val borderColor: Color) {
    Basic(Neutral900, Neutral200, Neutral200),
    Alert(Red500, Red300, Red500)
}

/**
 * EP EDS EdsBadge
 *
 * @param title the given text will be displayed to the Badge.
 * @param type the given [EdsBadgeType] will be applied to the Badge.
- if [EdsBadgeType.Basic] is set as the default type of Eds Badge.
- if [EdsBadgeType.Alert] is set the Eds Badge component will obtain an alert status and the color will change to red.
 */

@Composable
fun EdsBadge(
    title: String,
    type: EdsBadgeType = EdsBadgeType.Basic
) {
    Text(text = title,
        style = MaterialTheme.typography.body1,
        color = type.textColor,
        modifier = Modifier
            .background(
                color = type.backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, type.borderColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 1.dp)
    )
}