package com.educationperfect.pds_library.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.educationperfect.pds_library.R
import com.educationperfect.pds_library.ui.*

/**
 * Created by george on 03/07/2021
 */

enum class ActionState { Visible, Hidden }

@Composable
fun defaultTextButtonColors(): ButtonColors {
    return ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colors.primaryVariant,
        disabledContentColor = Neutral400
    )
}

sealed class NavigationIcon(
    val visible: Boolean = true,
    val enabled: Boolean,
    val icon: ImageVector,
    val tint: Color,
    val action: () -> Unit,
    val identifier: String = ""
) {
    data class IconBack(
        val isVisible: Boolean = true,
        val isEnabled: Boolean = true,
        val iconColor: Color = Blue500,
        val backAction: () -> Unit,
        val testId: String = "backButton"
    ) : NavigationIcon(
        visible = isVisible,
        enabled = isEnabled,
        icon = Icons.Rounded.ArrowBack,
        tint = iconColor,
        action = backAction,
        identifier = testId
    )

    data class IconClose(
        val isVisible: Boolean,
        val isEnabled: Boolean = true,
        val iconColor: Color = Blue500,
        val closeAction: () -> Unit,
        val testId: String = "closeButton"
    ) : NavigationIcon(
        visible = isVisible,
        enabled = isEnabled,
        icon = Icons.Rounded.Close,
        tint = iconColor,
        action = closeAction,
        identifier = testId
    )
}

data class MenuItemEntry(
    val title: String,
    val icon: Painter,
    val tint: Color,
    val action: () -> Unit
)

sealed class NavigationAction(val visible: Boolean = true, val identifier: String = "") {
    data class IconAction(
        val isVisible: Boolean,
        val icon: ImageVector,
        val tint: Color = Blue500,
        val action: () -> Unit,
        val testId: String = ""
    ) : NavigationAction(visible = isVisible, identifier = testId)

    data class TextAction(
        val isVisible: Boolean,
        val isEnabled: Boolean = true,
        val text: String,
        val action: () -> Unit,
        val testId: String = ""
    ) : NavigationAction(visible = isVisible, identifier = testId)

    data class MenuAction(
        val isVisible: Boolean,
        val isEnabled: Boolean = true,
        val isExpanded: Boolean,
        val icon: Painter,
        val menuItems: List<MenuItemEntry>,
        val tint: Color = Blue500,
        val action: () -> Unit,
        val dismiss: () -> Unit,
        val testId: String = ""
    ) : NavigationAction(visible = isVisible, identifier = testId)

    data class Progress(
        val isVisible: Boolean,
        val color: Color = Blue500,
        val strokeWidth: Dp = 2.dp,
        val testId: String = "progressIcon"
    ) : NavigationAction(visible = isVisible, identifier = testId)
}

@Composable
fun TopAppBarTitle(
    text: String,
    width: Dp = 300.dp,
    alpha: Float = 1f,
    style: TextStyle,
    color: Color,
    maxLines: Int = 1,
    modifier: Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .width(width)
            .alpha(alpha)
    )
}

@Composable
fun EpTopAppBar(
    title: String? = stringResource(R.string.nav_back),
    titleColor: Color = MaterialTheme.colors.primaryVariant,
    titleStyle: TextStyle = MaterialTheme.typography.h3,
    subTitle: String? = null,
    navigationIcon: NavigationIcon,
    actions: List<NavigationAction> = listOf(),
    actionSize: Dp = 24.dp,
    elevation: Dp = 2.dp,
    withBottomDivider: Boolean = true,
    backgroundColor: Color = Color.White
) {
    Column(Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                if (title != null) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TopAppBarTitle(
                            text = title,
                            modifier = Modifier,
                            style = titleStyle,
                            color = titleColor
                        )
                        if (subTitle != null) {
                            Text(
                                text = subTitle,
                                style = MaterialTheme.typography.caption,
                                color = Neutral700,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }
            },
            navigationIcon = {
                TopAppBarNavigationIcon(navIcon = navigationIcon)
            },
            actions = {
                TopAppBarActions(state = ActionState.Visible, actions = actions, actionSize = actionSize)
            },
            backgroundColor = backgroundColor,
            elevation = elevation
        )
        if (withBottomDivider) {
            Divider(
                color = Neutral300,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopAppBarNavigationIcon(navIcon: NavigationIcon) {
    if (navIcon.visible) {
        IconButton(onClick = navIcon.action, enabled = navIcon.enabled, modifier = Modifier.testTag(navIcon.identifier)) {
            Icon(
                imageVector = navIcon.icon,
                contentDescription = null,
                tint = if (navIcon.enabled) navIcon.tint else navIcon.tint.copy(0.4f),
            )
        }
    }
}

@Composable
fun TopAppBarActions(
    state: ActionState,
    actions: List<NavigationAction>,
    actionSize: Dp? = null
) {
    actions.forEach { action ->
        TopAppBarAction(state = state, actionSize = actionSize, navigationAction = action)
    }
}

@Composable
fun TopAppBarAction(
    state: ActionState,
    actionSize: Dp? = null,
    navigationAction: NavigationAction,
    textButtonColors: ButtonColors = defaultTextButtonColors()
) {
    val alpha by animateFloatAsState(if (state == ActionState.Visible) 1f else 0f)
    val size by animateDpAsState(if (state == ActionState.Visible) actionSize ?: 24.dp else 0.dp)

    if (navigationAction.visible) {
        when (navigationAction) {
            is NavigationAction.IconAction -> {
                IconButton(
                    onClick = navigationAction.action,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(size)
                        .height(size)
                        .alpha(alpha)
                        .testTag(navigationAction.identifier) //used testTag() so semantics that will be added and used by TalkBack make more sense to the user. Here the user will directly interact with this element
                ) {
                    Icon(
                        imageVector = navigationAction.icon,
                        contentDescription = null,
                        tint = navigationAction.tint
                    )
                }
            }
            is NavigationAction.MenuAction -> {
                Box {
                    IconButton(
                        onClick = navigationAction.action,
                        enabled = navigationAction.isEnabled,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .width(size)
                            .height(size)
                            .alpha(alpha)
                            .testTag(navigationAction.identifier)
                    ) {
                        Icon(
                            painter = navigationAction.icon,
                            contentDescription = null,
                            tint = if (navigationAction.isEnabled) navigationAction.tint else
                                navigationAction.tint.copy(0.4f)
                        )
                    }
                    DropdownMenu(
                        expanded = navigationAction.isExpanded,
                        onDismissRequest = navigationAction.dismiss,
                        offset = DpOffset(0.dp, 5.dp),
                        modifier = Modifier
                            .background(MaterialTheme.colors.surface)
                            .testTag("previewDropDown")
                    ) {
                        navigationAction.menuItems.forEachIndexed { index, menuItemEntry ->
                            DropdownMenuItem(onClick = menuItemEntry.action) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()) {
                                    Text(text = menuItemEntry.title,
                                        style = MaterialTheme.typography.h3,
                                        color = Neutral900
                                    )
                                    Spacer(Modifier.width(32.dp))
                                    Icon(menuItemEntry.icon,
                                        null,
                                        tint = menuItemEntry.tint,
                                        modifier = Modifier.size(24.dp))
                                }
                            }
                            if (index < navigationAction.menuItems.size - 1) {
                                Divider(thickness = 1.dp, color = Neutral300)
                            }
                        }
                    }
                }
            }

            is NavigationAction.TextAction -> {
                TextButton(
                    onClick = navigationAction.action,
                    colors = textButtonColors,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .alpha(alpha),
                    enabled = navigationAction.isEnabled
                ) {
                    Text(
                        text = navigationAction.text,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.h5
                        )
                }
            }
            is NavigationAction.Progress -> {
                CircularProgressIndicator(
                    strokeWidth = navigationAction.strokeWidth,
                    color = navigationAction.color,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(18.dp)
                        .height(18.dp)
                        .alpha(alpha)
                        .testTag(navigationAction.identifier)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EpTopAppBarWithSearch(
    isSearching: Boolean,
    query: String,
    title: String,
    titleColor: Color = MaterialTheme.colors.primaryVariant,
    titleStyle: TextStyle = MaterialTheme.typography.h3,
    placeholder: String,
    navigateUp: () -> Unit,
    onSearch: (String) -> Unit,
    onSearchAction: () -> Unit,
    onSearchChanged: (Boolean) -> Unit,
    withNavigationIcon: Boolean = true,
    navigationIcon: ImageVector = Icons.Rounded.ArrowBack,
    actions: List<NavigationAction> = listOf(),
    trailingSearch: Boolean = true,
    elevation: Dp = 2.dp,
    backgroundColor: Color = Color.White,
    textFieldId: String = "",
    withBottomDivider: Boolean = true
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = FocusRequester()
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
            text = query,
            selection = TextRange(query.length)
        )
        )
    }
    val titleWidth = 260.dp
    val showNavigationIcon = withNavigationIcon || isSearching
    val searchBarSize by animateDpAsState(if (isSearching) titleWidth else 0.dp)
    val searchBarAlpha by animateFloatAsState(if (isSearching) 1f else 0f)
    val titleAlpha by animateFloatAsState(if (isSearching) 0f else 1f)
    val searchAction = NavigationAction.IconAction(
        isVisible = true,
        icon = Icons.Rounded.Search,
        action = {
            onSearchAction()
            focusRequester.requestFocus()
            keyboardController?.show()
        },
        testId = "searchButton"
    )
    val actionState = when {
        isSearching -> ActionState.Hidden
        else -> ActionState.Visible
    }

    LaunchedEffect(Unit) {
        if (isSearching) {
            keyboardController?.show()
            focusRequester.requestFocus()
        }
    }

    Column(Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Box(Modifier.fillMaxHeight()) {
                    TopAppBarTitle(
                        text = title,
                        width = titleWidth,
                        alpha = titleAlpha,
                        color = titleColor,
                        style = titleStyle,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    if (isSearching && query.isEmpty()) {
                        TopAppBarTitle(
                            text = placeholder,
                            width = titleWidth,
                            alpha = searchBarAlpha,
                            style = MaterialTheme.typography.h3,
                            color = Neutral600,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    SearchBar(
                        value = textFieldValueState,
                        onValueChane = { value ->
                            textFieldValueState = value
                            onSearch(value.text)
                        },
                        size = searchBarSize,
                        alpha = searchBarAlpha,
                        focusRequester = focusRequester,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .testTag(textFieldId)
                    )
                }
            },
            navigationIcon = if (showNavigationIcon) {
                {
                    SearchNavigationIcon(
                        isSearching = isSearching,
                        withNavigationIcon = withNavigationIcon,
                        icon = navigationIcon,
                        onStateChange = onSearchChanged,
                        action = navigateUp
                    )
                }
            } else null,
            actions = {
                if (trailingSearch) {
                    TopAppBarActions(state = actionState, actions = actions)
                    TopAppBarAction(state = actionState, navigationAction = searchAction)
                }
                else {
                    TopAppBarAction(state = actionState, navigationAction = searchAction)
                    TopAppBarActions(state = actionState, actions = actions)
                }
            },
            backgroundColor = backgroundColor,
            elevation = elevation
        )
        if (withBottomDivider) {
            Divider(
                color = Neutral300,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalUnitApi::class
)
@Composable
fun SearchBar(
    value: TextFieldValue,
    onValueChane: (TextFieldValue) -> Unit,
    size: Dp,
    alpha: Float,
    focusRequester: FocusRequester,
    modifier: Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = onValueChane,
        enabled = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            autoCorrect = false,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        maxLines = 1,
        singleLine = true,
        cursorBrush = SolidColor(Color(0xFF3838EA)),
        textStyle = MaterialTheme.typography.h4,
        modifier = modifier
            .width(size)
            .alpha(alpha)
            .focusRequester(focusRequester)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchNavigationIcon(
    isSearching: Boolean,
    withNavigationIcon: Boolean,
    icon: ImageVector,
    onStateChange: (Boolean) -> Unit,
    action: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    return IconButton(onClick = {
        when {
            isSearching -> {
                onStateChange(false)
                keyboardController?.hide()
            }
            else -> if (withNavigationIcon) action()
        }
    },
        modifier = Modifier.testTag("backButton"))
    {
        val navIcon = if (isSearching) Icons.Rounded.ArrowBack else icon
        Icon(
            imageVector = navIcon,
            contentDescription = null,
            tint = MaterialTheme.colors.primaryVariant
        )
    }
}