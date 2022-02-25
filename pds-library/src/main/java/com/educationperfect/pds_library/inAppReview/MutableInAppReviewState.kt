package com.educationperfect.pds_library.inAppReview

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by George Sylaios on 16/2/22.
 */

@Composable
internal fun rememberMutableInAppReviewState(): InAppReviewStateResult {

    val context = LocalContext.current
    val appReviewManager = LocalAppReviewManager.current ?: ReviewManagerFactory.create(context)
    val scope = rememberCoroutineScope()

    val inAppReviewState = remember {
        MutableInAppReviewState(scope, appReviewManager)
    }

    return InAppReviewStateResult(inAppReviewState, appReviewManager)
}

internal class MutableInAppReviewState(
    scope: CoroutineScope,
    private val appReviewManager: ReviewManager
): InAppReviewState {

    private var _appReviewResult by mutableStateOf<ReviewInfo?>(null)

    override val appReviewResult: ReviewInfo?
        get() = _appReviewResult

    init {
        scope.launch {
            val request = appReviewManager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _appReviewResult = task.result
                } else {
                    val reviewErrorCode = task.exception?.message
                    Timber.e("In app review error with error code $reviewErrorCode")
                }
            }
        }
    }
}

data class InAppReviewStateResult(val reviewState: InAppReviewState, val manager: ReviewManager)