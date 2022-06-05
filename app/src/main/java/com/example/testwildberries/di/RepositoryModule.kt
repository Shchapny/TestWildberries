package com.example.testwildberries.di

import com.example.testwildberries.repository.TravelRepository
import com.example.testwildberries.repository.TravelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindTravelRepository(impl: TravelRepositoryImpl): TravelRepository
}