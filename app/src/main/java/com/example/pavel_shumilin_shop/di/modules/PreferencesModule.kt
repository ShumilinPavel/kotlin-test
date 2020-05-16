package com.example.pavel_shumilin_shop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.pavel_shumilin_shop.data.SavedToCartProductsDaoImpl
import com.example.pavel_shumilin_shop.domain.SavedToCartProductsDao
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
    fun provideSavedToCartProductsDao(preferences: SharedPreferences): SavedToCartProductsDao =
        SavedToCartProductsDaoImpl(preferences)
}