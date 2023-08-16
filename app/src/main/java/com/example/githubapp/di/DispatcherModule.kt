package com.example.githubapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    fun providesIODispatcher() = CoroutineAppDispatcher().io
}
