package com.educationperfect.pds_library.inAppUpdate

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by George Sylaios on 25/2/22.
 */

@Composable
internal fun rememberMutableInAppUpdateState(): InAppUpdateState {

    val context = LocalContext.current
    val appUpdateManager = LocalAppUpdateManager.current ?: AppUpdateManagerFactory.create(context)
    val scope = rememberCoroutineScope()

    val inAppUpdateState = remember {
        MutableInAppUpdateState(scope, appUpdateManager)
    }

    return inAppUpdateState
}

internal class MutableInAppUpdateState(
    scope: CoroutineScope,
    private val appUpdateManager: AppUpdateManager,
) : InAppUpdateState {

    private var _appUpdateResult by mutableStateOf<AppUpdateResult>(AppUpdateResult.NotAvailable)

    override val appUpdateResult: AppUpdateResult
        get() = _appUpdateResult

    init {
        scope.launch {
            appUpdateManager.requestUpdateFlow().catch { e ->
                Timber.tag("error").e(e)
            }.collect {
                _appUpdateResult = it
            }
        }
    }
}