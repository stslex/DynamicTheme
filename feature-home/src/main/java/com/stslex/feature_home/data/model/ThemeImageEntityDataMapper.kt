package com.stslex.feature_home.data.model

import android.net.Uri
import com.stslex.core.Mapper
import com.stslex.core_data_source.model.ThemeImageEntity
import com.stslex.feature_home.domain.ThemeType

interface ThemeImageEntityDataMapper : Mapper<ThemeImageEntity, ThemeImageDataModel> {

    class Base : ThemeImageEntityDataMapper {

        override fun invoke(data: ThemeImageEntity): ThemeImageDataModel = with(data) {
            ThemeImageDataModel(
                themeType = ThemeType.getTypeOfValue(themeType.isDark),
                uri = Uri.parse(uri)
            )
        }
    }
}