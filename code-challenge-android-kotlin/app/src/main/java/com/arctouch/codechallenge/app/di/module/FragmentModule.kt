package com.arctouch.codechallenge.app.di.module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.arctouch.codechallenge.base.di.scope.FragmentScope
import com.arctouch.codechallenge.home.presentation.ui.HomeFragment

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment


}