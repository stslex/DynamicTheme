package com.stslex.dynamictheme.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.stslex.core.HandlersExt.coroutineHandler
import com.stslex.core_ui.AppTheme
import com.stslex.dynamictheme.ui.shortcut.ShortcutBuilder
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val shortcutBuilder: ShortcutBuilder by inject()

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