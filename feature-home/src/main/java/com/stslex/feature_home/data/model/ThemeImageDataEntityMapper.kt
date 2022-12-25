package com.stslex.feature_home.data.model

import com.stslex.core.Mapper
import com.stslex.core_data_source.model.ThemeImageEntity
import com.stslex.core_data_source.model.ThemeTypeEntity

//TODO coin can't resolve generic type, Need to be replace with qualifier
interface ThemeImageDataEntityMapper : Mapper<ThemeImageDataModel, ThemeImageEntity> {

    class Base : ThemeImageDataEntityMapper {

        override fun invoke(data: ThemeImageDataModel): ThemeImageEntity = with(data) {
            ThemeImageEntity(
                themeType = ThemeTypeEntity.getTypeOfValue(themeType.isDark),
                uri = uri.toString()
            )
        }
    }
}