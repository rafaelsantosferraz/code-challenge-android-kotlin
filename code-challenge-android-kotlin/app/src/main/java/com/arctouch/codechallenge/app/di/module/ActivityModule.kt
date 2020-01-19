package com.arctouch.codechallenge.app.di.module


import com.arctouch.codechallenge.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.arctouch.codechallenge.base.di.scope.ActivityScope

@Module
abstract class ActivityModule {

    // Main
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity



}