package com.stslex.feature_home.ui.vm

import android.net.Uri
import com.stslex.feature_home.domain.ThemeType
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MockFeatureHomeViewModel : HomeFeatureAbstractionViewModel {

    private val _themeImageListFlow = MutableStateFlow(
        ThemeType.values().associateWith { ThemeImageUIModel(it, Uri.parse("")) }
    )

    override val themeImageListFlow: StateFlow<Map<ThemeType, ThemeImageUIModel>>
        get() = _themeImageListFlow.asStateFlow()

    override fun pickImage(image: ThemeImageUIModel) {
        _themeImageListFlow.update { map ->
            map.toMutableMap().apply {
                set(image.type, image)
            }.toSortedMap()
        }
    }
}