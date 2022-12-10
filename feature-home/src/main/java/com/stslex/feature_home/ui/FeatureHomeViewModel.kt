package com.stslex.feature_home.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.AppLogger
import com.stslex.feature_home.domain.FeatureHomeInteractor
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import com.stslex.feature_home.ui.utils.ImagePicker
import com.stslex.feature_home.ui.utils.ImagePickerImpl
import com.stslex.feature_home.ui.vm.HomeFeatureAbstractionViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeName

class FeatureHomeViewModel(
    private val interactor: FeatureHomeInteractor
) : ViewModel(), HomeFeatureAbstractionViewModel {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, e ->
        handleError(e = e, scope = context.getScopeName().value)
    }

    override val themeImageListFlow: Flow<Map<ThemeUIType, ThemeImageUIModel>>
        get() = interactor.getAllThemeImage()
            .catch { e -> handleError(e, this.getScopeName().value) }
            .flowOn(Dispatchers.IO)

    override fun pickImage(type: ThemeUIType?, uri: Uri?) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val correctUri = uri ?: throw IllegalArgumentException("Uri is null")
            val correctType = type ?: throw IllegalArgumentException("ThemeUIType is null")
            val image = ThemeImageUIModel(correctType, correctUri)
            interactor.setThemeImage(image)
        }
    }

    val imagePicker: ImagePicker by lazy {
        ImagePickerImpl(::pickImage)
    }

    private fun handleError(e: Throwable, scope: String = String()) {
        AppLogger.logException(
            throwable = e,
            scope = scope
        )
    }
}