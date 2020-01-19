package com.arctouch.codechallenge.home.di.modules

import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.base.di.ViewModelKey
import com.arctouch.codechallenge.base.di.scope.FragmentScope
import com.arctouch.codechallenge.home.presentation.ui.list.HomeFragment
import com.arctouch.codechallenge.home.presentation.ui.list.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class HomeModule {


    @Binds
    @Singleton
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

}