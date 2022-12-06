package com.stslex.feature_home.ui.model

import android.net.Uri
import com.stslex.feature_home.domain.ThemeType

data class ThemeImageUIModel(
    val type: ThemeType,
    val uri: Uri? = null
)
