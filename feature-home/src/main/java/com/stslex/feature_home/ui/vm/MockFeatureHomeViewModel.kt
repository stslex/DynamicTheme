package com.stslex.feature_home.ui.vm

import android.net.Uri
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MockFeatureHomeViewModel {

    private val _themeImageListFlow = MutableStateFlow(
        ThemeUIType.values().associateWith { ThemeImageUIModel(it) }
    )

    val themeImageListFlow: StateFlow<Map<ThemeUIType, ThemeImageUIModel>>
        get() = _themeImageListFlow.asStateFlow()

    fun pickImage(type: ThemeUIType?, uri: Uri?) {
        if (type == null || uri == null) return
        _themeImageListFlow.update { map ->
            map.toMutableMap().apply {
                set(type, ThemeImageUIModel(type, uri))
            }.toSortedMap()
        }
    }
}