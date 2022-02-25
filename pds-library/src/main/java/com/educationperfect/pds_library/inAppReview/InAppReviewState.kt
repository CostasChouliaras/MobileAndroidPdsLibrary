package com.educationperfect.pds_library.inAppReview

import androidx.compose.runtime.Composable
import com.google.android.play.core.review.ReviewInfo

/**
 * Created by George Sylaios on 16/2/22.
 */

interface InAppReviewState {
    val appReviewResult: ReviewInfo?
}

@Composable
fun rememberInAppReviewState(): InAppReviewStateResult {
    return rememberMutableInAppReviewState()
}