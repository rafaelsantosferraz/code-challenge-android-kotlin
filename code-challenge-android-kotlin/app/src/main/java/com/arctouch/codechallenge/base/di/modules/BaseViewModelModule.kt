package com.arctouch.codechallenge.base.di.modules

import androidx.lifecycle.ViewModelProvider
import com.arctouch.codechallenge.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}