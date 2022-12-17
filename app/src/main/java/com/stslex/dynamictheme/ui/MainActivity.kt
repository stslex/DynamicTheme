package com.stslex.dynamictheme.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.stslex.core.AppLogger
import com.stslex.core_ui.AppTheme
import com.stslex.dynamictheme.ui.shortcut.ShortcutBuilder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.getScopeName

class MainActivity : AppCompatActivity() {

    private val shortcutBuilder: ShortcutBuilder by inject()
    private val coroutineHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        AppLogger.logException(throwable, coroutineContext.getScopeName().value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleScope.launch(coroutineHandler) {
            shortcutBuilder()
        }
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppInit()
            }
        }
    }
}