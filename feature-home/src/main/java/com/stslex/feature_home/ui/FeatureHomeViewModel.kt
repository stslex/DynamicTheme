package com.stslex.feature_home.ui

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.stslex.core.AppDispatcher
import com.stslex.core_ui.BaseViewModel
import com.stslex.feature_home.domain.FeatureHomeInteractor
import com.stslex.feature_home.domain.initialUIList
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.utils.mapUriByType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class FeatureHomeViewModel(
    private val interactor: FeatureHomeInteractor,
    appDispatcher: AppDispatcher
) : BaseViewModel(appDispatcher) {

    private val _themeImageListFlow: MutableStateFlow<List<ThemeImageUIModel>> =
        MutableStateFlow(initialUIList)

    val themeImageListFlow: Flow<List<ThemeImageUIModel>>
        get() = _themeImageListFlow

    val selectedImage: ThemeImageUIModel?
        get() = _themeImageListFlow.value.firstOrNull { it.isSelected }

    init {
        interactor.getAllThemeImage()
            .primaryFlow()
            .onEach { imageList ->
                _themeImageListFlow.update { mapImage ->
                    mapImage.mapUriByType(imageList)
                }
            }.launchIn(viewModelScope)
    }

    fun onImagePicked(uri: Uri?) {
        launchCoroutine {
            val image = selectedImage?.copy(
                uri = checkNotNull(uri) { ERR_URI_NULL }
            ) ?: return@launchCoroutine
            interactor.setThemeImage(image)
        }
    }

    fun onImageDeleteClicked() {
        launchCoroutine {
            val image = selectedImage ?: return@launchCoroutine
            interactor.deleteImage(image.type)
        }
    }

    fun onCardSelect() {
        _themeImageListFlow.update { list ->
            list.map { image -> image.onSelect() }
        }
    }

    companion object {
        private const val ERR_URI_NULL = "Uri is null"
    }
}