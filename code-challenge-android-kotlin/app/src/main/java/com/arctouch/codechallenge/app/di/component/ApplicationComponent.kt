package com.arctouch.codechallenge.app.di.component

import android.app.Application
import com.arctouch.codechallenge.app.App
import com.arctouch.codechallenge.app.di.module.ActivityModule
import com.arctouch.codechallenge.base.di.modules.BaseViewModelModule
import com.arctouch.codechallenge.home.di.modules.HomeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,

    BaseViewModelModule::class,

    HomeModule::class
])


interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent

    }

    fun inject(app: App)

}
