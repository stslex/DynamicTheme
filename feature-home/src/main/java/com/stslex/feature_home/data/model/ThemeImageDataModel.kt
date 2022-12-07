package com.stslex.feature_home.data.model

import android.net.Uri
import com.stslex.feature_home.domain.ThemeType

data class ThemeImageDataModel(
    val themeType: ThemeType,
    val uri: Uri
)