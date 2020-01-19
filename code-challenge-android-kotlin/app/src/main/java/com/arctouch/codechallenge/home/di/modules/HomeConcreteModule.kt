package com.arctouch.codechallenge.home.di.modules

import com.arctouch.codechallenge.home.data.datasources.RemoteMoviesDataSource
import com.arctouch.codechallenge.home.data.repositories.MoviesRepositoryImpl
import com.arctouch.codechallenge.home.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeConcreteModule {


    @Provides
    @Singleton
    fun providesRemoteMoviesRepository(remoteMoviesDataSource: RemoteMoviesDataSource ): MoviesRepository {
        return MoviesRepositoryImpl(remoteMoviesDataSource)
    }

    @Provides
    @Singleton
    fun providesRemoteMoviesDataSource(): RemoteMoviesDataSource {
        return RemoteMoviesDataSource()
    }
}