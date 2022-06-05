package com.example.testwildberries.di

import com.example.testwildberries.api.TravelApi
import com.example.testwildberries.api.loggingInterceptor
import com.example.testwildberries.api.okHttpClient
import com.example.testwildberries.api.retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(): TravelApi {
        return retrofit(okHttpClient(loggingInterceptor()))
            .create(TravelApi::class.java)
    }
}