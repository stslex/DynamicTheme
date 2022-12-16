package com.stslex.dynamictheme.ui.shortcut

import android.content.Context
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.stslex.core.AppLogger
import com.stslex.dynamictheme.reciever.ChangeThemeRuntimeService.Companion.KEY_THEME_TYPE
import com.stslex.dynamictheme.ui.MainActivity

class ShortcutBuilderImpl(private val context: Context) : ShortcutBuilder {

    override fun invoke() {
        try {
            Shortcut.values().forEach(::setupShortcut)
        } catch (e: Exception) {
            AppLogger.logException(e, javaClass.simpleName)
        }
    }

    private fun setupShortcut(shortcut: Shortcut) {
        ShortcutManagerCompat.pushDynamicShortcut(context, shortcut.shortcutInfoCompat)
    }

    private val Shortcut.shortcutInfoCompat: ShortcutInfoCompat
        get() = ShortcutInfoCompat.Builder(context, context.getString(labelSource))
            .setShortLabel(context.getString(shortLabelSource))
            .setLongLabel(context.getString(longLabelSource))
            .setDisabledMessage(context.getString(disabledMessageSource))
            .setIcon(IconCompat.createWithResource(context, iconSource))
            .setIntent(shortcutIntent)
            .build()


    private val Shortcut.shortcutIntent: Intent
        get() = Intent(context, MainActivity::class.java).apply {
            putExtra(KEY_THEME_TYPE, this@shortcutIntent.type.name)
        }
}