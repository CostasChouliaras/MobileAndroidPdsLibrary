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

/**
 * EP EDS top app bar title
 *
 * @param text The input text to be shown in the title of top the app bar.
 * @param width The given Dp value will be set as the width of the top app bar.
 * @param alpha The fraction of children's alpha value and must be between 0 and 1, inclusive.
 * @param style Style configuration for the text such as color, font, line height etc.
 * @param color [Color] to apply to the text. If [Color.Unspecified], and style has no color set, this will be [LocalContentColor].
 * @param maxLines An optional maximum number of lines for the text to span, wrapping if necessary.
 * If the text exceeds the given number of lines, it will be truncated according to overflow and softWrap. If it is not null, then it must be greater than zero.
 * @param modifier [Modifier] to apply to this layout node.
 *
 */
@Composable
private fun TopAppBarTitle(
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

/**
 * EP EDS EP top app bar
 *
 * @param title The input text will be shown in the [title] of top the app bar.
 * @param titleColor The given color value will be set as the color for the [title] of the top app bar.
 * @param titleStyle Style configuration for the title's text value such as color, font, line height etc.
 * @param subTitle The given text value will be present as the top app bar's subtitle.
 * @param navigationIcon The given [NavigationIcon] will be added to the top app bar.
 * @param actions The actions displayed at the end of the [TopAppBar]. This should typically be [IconButtons].
 * The default layout here is a Row, so icons inside will be placed horizontally.
 * @param actionSize The actions displayed at the end of the TopAppBar. This should typically be IconButtons.
 * The default layout here is a Row, so icons inside will be placed horizontally.
 * @param elevation The elevation of this TopAppBar.
 * @param withBottomDivider Defines if the top app bar will have a bottom divider.
 * @param backgroundColor The background color for the TopAppBar. Use Color.Transparent to have no color.
 *
 */
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

/**
 * EP EDS top app bar navigation icon.
 * @param navIcon [ImageVector] to draw inside this Icon.
 */
@Composable
private fun TopAppBarNavigationIcon(navIcon: NavigationIcon) {
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

/**
 * EP EDS top app bar actions
 *
 * @param state Defines the state of the action bar.
 * @param actions The actions displayed at the end of the [TopAppBar]. This should typically be [IconButtons].
 * The default layout here is a Row, so icons inside will be placed horizontally.
 * @param actionSize actionSize The actions displayed at the end of the [TopAppBar]. This should typically be IconButtons.
 * The default layout here is a Row, so icons inside will be placed horizontally.
 *
 */

@Composable
private fun TopAppBarActions(
    state: ActionState,
    actions: List<NavigationAction>,
    actionSize: Dp? = null
) {
    actions.forEach { action ->
        TopAppBarAction(state = state, actionSize = actionSize, navigationAction = action)
    }
}


/**
 * EP EDS top app bar action
 *
 * @param state Defines the state of the action bar.
 * @param actionSize actionSize The actions displayed at the end of the TopAppBar. This should typically be IconButtons.
 * The default layout here is a Row, so icons inside will be placed horizontally.
 * @param navigationAction Receives a navigation action.
 * @param textButtonColors Text to be displayed as the description of the Dialog.
 *
 */

@Composable
private fun TopAppBarAction(
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

/**
 * EP EDS EP top app bar with search
 *
 * @param isSearching Defines if the top app bar will be on searching mode
 * @param query Text value which will be used for search.
 * @param title Text to be displayed as the title of the top app bar.
 * @param titleColor Defines the color for the title of the top app bar.
 * @param titleStyle Style configuration for the title's text value such as color, font, line height etc.
 * @param placeholder Text to be displayed as the placeholder text when the top app bar is on searching mode.
 * @param navigateUp The callback to be invoked when the back button is tapped.
 * @param onSearch Defines the callback to be invoked when the top app bar is searching.
 * @param onSearchAction Defines the callback to be invoked when the user selects an item from the searching list.
 * @param onSearchChanged Defines if the text value on search bar has changed.
 * @param withNavigationIcon Defines if the top app bar will have a navigation icon.
 * @param navigationIcon Defines the navigation icon of the searchbar.
 * @param actions The actions displayed at the end of the [TopAppBar]. This should typically be [IconButtons].
 * The default layout here is a Row, so icons inside will be placed horizontally.
 * @param trailingSearch Defines if the top app bar will have a search icon.
 * @param elevation The elevation of this TopAppBar.
 * @param backgroundColor The background color for the TopAppBar. Use Color.Transparent to have no color.
 * @param textFieldId Applies a tag to allow modified element to be found in tests.
 * This is a convenience method for a semantics that sets [SemanticsPropertyReceiver.testTag].
 * @param withBottomDivider Defines if the top app bar will have a bottom divider.
 *
 */

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

/**
 * EP EDS Seach bar
 *
 * @param value The []androidx.compose.ui.text.input.TextFieldValue] to be shown in the search bar.
 * @param onValueChane Called when the input service updates the values in the search bar.
 * @param size The given [Dp] value will be considered as the size of the search bar.
 * @param alpha The fraction of children's alpha value and must be between 0 and 1, inclusive.
 * @param focusRequester Add this modifier to request changes to focus of the search bar.
 * @param modifier [Modifier] for this search bar.
 */

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalUnitApi::class
)
@Composable
private fun SearchBar(
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

/**
 * EP EDS SearchNavigationIcon
 *
 * @param isSearching Defines if the icon will change to searching mode.
 * @param withNavigationIcon Defines if a navigation icon will be present.
 * @param icon Defines the icon that will be used.
 * @param onStateChange The callback to be invoked when the state of the composable change.
 * @param action The callback to be invoked when this composable will be tapped.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchNavigationIcon(
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