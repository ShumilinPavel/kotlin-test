package com.example.pavel_shumilin_shop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.pavel_shumilin_shop.data.ViewedProductDaoImpl
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideViewedProduct(preferences: SharedPreferences): ViewedProductDao =
        ViewedProductDaoImpl(preferences)
}