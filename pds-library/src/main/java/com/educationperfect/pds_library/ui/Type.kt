package com.educationperfect.pds_library.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val EdsTypography = Typography(
    defaultFontFamily = FontFamily.Default,

    //title 1
    h1 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.4.sp
    ),
    //title 2
    h2 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.25.sp
    ),
    //Cell item
    h3 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 17.sp,
        lineHeight = (19.92).sp
    ),
    //Paragraph / Text input
    h4 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 17.sp,
        lineHeight = (19.92).sp
    ),
    //Button
    h5 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = (18.75).sp
    ),
    //Toast message
    h6 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 15.sp,
        lineHeight = (17.58).sp
    ),
    //Subtitle
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 15.sp,
        lineHeight = (17.58).sp
    ),
    //Text field name
    subtitle2 = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        lineHeight = (17.58).sp
    ),
    //Badge
    body1 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        letterSpacing = (-0.05).sp
    ),
    //Title details
    body2 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        letterSpacing = (-0.05).sp
    ),
    //Section header
    button = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 13.sp,
        lineHeight = (15.23).sp,
        letterSpacing = 0.2.sp
    ),
    //Text field caption
    caption = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        letterSpacing = (-0.05).sp
    ),
    //Avatar
    overline = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

val annotatedNormalType = SpanStyle(
    color = Midnight500,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    fontFamily = FontFamily.Default
)

val annotatedUrlType = SpanStyle(
    color = Blue500,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    fontFamily = FontFamily.Default
)