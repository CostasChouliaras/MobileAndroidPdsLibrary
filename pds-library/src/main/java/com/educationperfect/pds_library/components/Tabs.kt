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

/**
 * EP EDS EpTabRow
 *
 * @param items the given list items will be added as tabs.
 * @param tabIndex the index of the currently selected tab.
 * @param backgroundColor The background color for the TabRow. Use [Color.Transparent] to have no color.
 * @param contentColor The preferred content color provided by this TabRow to its children.
 * Defaults to either the matching content color for [backgroundColor], or if [backgroundColor] is not a color from the theme,
 * this will keep the same value set above this TabRow.
 * @param width the given value will be considered as the width of the Row
 * @param onClick the callback to be invoked when this tab is selected
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

/**
 * EP EDS EpTabItem. Custom tab item. Tabs organize content across different screens, data sets, and other interactions.
 * Tab represents a single page of content using a text label.
 *
 * @param selected whether this tab is selected or not
 * @param text the text label displayed in this tab
 * @param onClick the callback to be invoked when this tab is selected
 */

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