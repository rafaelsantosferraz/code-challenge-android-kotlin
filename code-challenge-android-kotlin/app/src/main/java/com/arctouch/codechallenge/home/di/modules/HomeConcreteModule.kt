package com.arctouch.codechallenge.home.di.modules

import com.arctouch.codechallenge.home.data.datasources.LocalMoviesDataSource
import com.arctouch.codechallenge.home.data.datasources.RemoteMoviesDataSource
import com.arctouch.codechallenge.home.data.repositories.MoviesRepositoryImpl
import com.arctouch.codechallenge.home.domain.repositories.MoviesRepository
import com.arctouch.codechallenge.home.presentation.network.TmdbApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class HomeConcreteModule {


    @Provides
    @Singleton
    fun providesRemoteMoviesRepository(remoteMoviesDataSource: RemoteMoviesDataSource, localMoviesDataSource: LocalMoviesDataSource): MoviesRepository {
        return MoviesRepositoryImpl(remoteMoviesDataSource, localMoviesDataSource)
    }

    @Provides
    @Singleton
    fun providesRemoteMoviesDataSource(api: TmdbApi): RemoteMoviesDataSource {
        return RemoteMoviesDataSource(api)
    }

    @Provides
    @Singleton
    fun providesLocalMoviesDataSource(): LocalMoviesDataSource {
        return LocalMoviesDataSource
    }

    @Provides
    @Singleton
    fun providesMoviesApi(okHttpClient: OkHttpClient): TmdbApi {
        return Retrofit.Builder()
                .baseUrl(TmdbApi.URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(TmdbApi::class.java)
    }
}