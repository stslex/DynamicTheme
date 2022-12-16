package com.stslex.dynamictheme.ui.widget

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.io.OutputStream

object ThemeWidgetInfoStateDefinition : GlanceStateDefinition<ThemeWidgetState> {

    private const val DATA_STORE_FILENAME = "widgetState"
    private val Context.datastore by dataStore(DATA_STORE_FILENAME, ThemeInfoSerializer)

    override suspend fun getDataStore(
        context: Context,
        fileKey: String
    ): DataStore<ThemeWidgetState> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }

    object ThemeInfoSerializer : Serializer<ThemeWidgetState> {

        override val defaultValue = ThemeWidgetState.Failed

        override suspend fun readFrom(input: InputStream): ThemeWidgetState = try {
            Json.decodeFromString(
                ThemeWidgetState.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("Could not read data: ${exception.message}")
        }

        override suspend fun writeTo(t: ThemeWidgetState, output: OutputStream) {
            output.use {
                it.write(
                    Json.encodeToString(ThemeWidgetState.serializer(), t).encodeToByteArray()
                )
            }
        }
    }
}