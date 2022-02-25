package com.educationperfect.pds_library.inAppUpdate

import androidx.compose.runtime.Composable
import com.google.android.play.core.ktx.AppUpdateResult

/**
 * Created by George Sylaios on 25/2/22.
 */

@Composable
fun rememberInAppUpdateState(): InAppUpdateState {
    return rememberMutableInAppUpdateState()
}

interface InAppUpdateState {
    val appUpdateResult: AppUpdateResult
}