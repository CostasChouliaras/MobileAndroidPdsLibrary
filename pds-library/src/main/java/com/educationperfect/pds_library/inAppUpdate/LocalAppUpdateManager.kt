package com.educationperfect.pds_library.inAppUpdate

import androidx.compose.runtime.staticCompositionLocalOf
import com.google.android.play.core.appupdate.AppUpdateManager

/**
 * Created by George Sylaios on 25/2/22.
 */

internal val LocalAppUpdateManager = staticCompositionLocalOf<AppUpdateManager?> {
    null
}