package com.educationperfect.pds_library.inAppReview

import androidx.compose.runtime.staticCompositionLocalOf
import com.google.android.play.core.review.ReviewManager

/**
 * Created by George Sylaios on 16/2/22.
 */

internal val LocalAppReviewManager = staticCompositionLocalOf<ReviewManager?> { null }