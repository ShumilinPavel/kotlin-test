package com.example.pavel_shumilin_shop.di

import android.content.Context
import com.example.pavel_shumilin_shop.di.modules.MainApiModule
import com.example.pavel_shumilin_shop.di.modules.PreferencesModule
import com.example.pavel_shumilin_shop.ui.cart.CartActivity
import com.example.pavel_shumilin_shop.ui.catalog.CatalogActivity
import com.example.pavel_shumilin_shop.ui.detailed.DetailedActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        MainApiModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: CatalogActivity)
    fun inject(activity: DetailedActivity)
    fun inject(activity: CartActivity)
}