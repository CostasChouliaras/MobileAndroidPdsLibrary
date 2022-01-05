package com.educationperfect.pds_library.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(3.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(12.dp)
)

val BottomSheetShape = RoundedCornerShape(
    topStart = 18.dp,
    topEnd = 18.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp
)