package com.example.githubapp.di

import com.example.githubapp.data.mapper.ErrorMapper
import com.example.githubapp.data.mapper.RepositoryMapper
import com.example.githubapp.data.mapper.SearchUserMapper
import com.example.githubapp.data.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun providesUserMapper() = UserMapper()

    @Provides
    fun provideRepositoryMapper() = RepositoryMapper()

    @Provides
    fun provideSearchMapper() = SearchUserMapper(providesUserMapper())

    @Provides
    fun providesErrorMapper() = ErrorMapper()
}