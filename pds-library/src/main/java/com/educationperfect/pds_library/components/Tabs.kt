package com.educationperfect.pds_library.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.educationperfect.pds_library.ui.Neutral100
import com.educationperfect.pds_library.ui.Neutral700

/**
 * Created by george on 24/08/2021
 */

@Composable
fun EpTabRow(
    items: List<String>,
    tabIndex: Int,
    backgroundColor: Color = Neutral100,
    contentColor: Color = MaterialTheme.colors.primaryVariant,
    width: Dp = 200.dp,
    onClick: (Int) -> Unit
) {
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        Box(
            modifier = Modifier
                .tabIndicatorOffset(tabPositions[tabIndex])
                .height(4.dp)
                .fillMaxSize()
                .border(
                    BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
                    RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                )
        )
    }

    TabRow(
        selectedTabIndex = tabIndex,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        indicator = indicator,
        divider = {},
        modifier = Modifier.width(width)
    ) {
        items.forEachIndexed { index, text ->
            EpTabItem(
                selected = tabIndex == index,
                text = text,
                onClick = { onClick(index) }
            )
        }
    }
}

@Composable
fun EpTabItem(
    selected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    val color = if (selected) MaterialTheme.colors.primaryVariant else Neutral700

    Tab(
        selected = selected,
        onClick = onClick,
        text = {
            Text(
                text = text,
                color = color,
                style = MaterialTheme.typography.subtitle2
            )
        }
    )
}