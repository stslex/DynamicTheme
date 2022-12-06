package com.stslex.feature_home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.feature_home.domain.FeatureHomeInteractor
import com.stslex.feature_home.domain.ThemeType
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.vm.HomeFeatureAbstractionViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeName

class FeatureHomeViewModel(
    private val interactor: FeatureHomeInteractor
) : ViewModel(), HomeFeatureAbstractionViewModel {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
            Log.e(
                "${javaClass.simpleName}: ${coroutineContext.getScopeName()}",
                throwable.message,
                throwable.cause
            )
        }

    override val themeImageListFlow: StateFlow<Map<ThemeType, ThemeImageUIModel>?>
        get() = interactor.getAllThemeImage().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    override fun pickImage(image: ThemeImageUIModel) {
        if (image.uri == null || image.uri.path.isNullOrBlank()) return
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            interactor.setThemeImage(image)
        }
    }
}