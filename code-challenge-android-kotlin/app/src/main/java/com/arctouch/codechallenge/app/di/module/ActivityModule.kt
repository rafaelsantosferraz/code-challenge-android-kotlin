package com.arctouch.codechallenge.app.di.module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.arctouch.codechallenge.base.di.scope.ActivityScope
import com.arctouch.codechallenge.home.presentation.ui.HomeActivity

@Module
abstract class ActivityModule {

    // Main
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): HomeActivity



}