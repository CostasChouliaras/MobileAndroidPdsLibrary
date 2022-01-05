package com.educationperfect.pds_library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.AvatarDefault

/**
 * Created by george on 16/08/2021
 */

enum class EdsAvatarType(val radius: Dp, val padding: Dp) {
    List(8.dp, 5.dp),
    Basic(12.dp, 11.dp)
}

@Composable
fun Avatar(
    initials: String,
    type: EdsAvatarType = EdsAvatarType.Basic,
    style: TextStyle = MaterialTheme.typography.h2,
    backgroundColor: Color = AvatarDefault,
    width: Dp = 28.dp,
    height: Dp = 28.dp
) {
    Box(
        modifier = Modifier
            .graphicsLayer { rotationZ = -8f }
            .clip(RoundedCornerShape(type.radius))
            .background(backgroundColor)
            .width(width)
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials.uppercase(),
            textAlign = TextAlign.Center,
            style = style,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(type.padding)
                .graphicsLayer { rotationZ = 8f }
        )
    }
}

