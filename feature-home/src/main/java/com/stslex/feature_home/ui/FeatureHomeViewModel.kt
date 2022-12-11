package com.stslex.feature_home.ui

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.stslex.core_ui.BaseViewModel
import com.stslex.feature_home.domain.FeatureHomeInteractor
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import com.stslex.feature_home.ui.utils.mapUriByType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeatureHomeViewModel(
    private val interactor: FeatureHomeInteractor
) : BaseViewModel() {

    private val _themeImageListFlow = MutableStateFlow(
        ThemeUIType.values().map { type ->
            ThemeImageUIModel(
                type = type,
                isSelected = type == ThemeUIType.DARK
            )
        }
    )

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
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val image = selectedImage?.copy(
                uri = checkNotNull(uri) { ERR_URI_NULL }
            ) ?: return@launch
            interactor.setThemeImage(image)
        }
    }

    fun onImageDeleteClicked() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val image = selectedImage ?: return@launch
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