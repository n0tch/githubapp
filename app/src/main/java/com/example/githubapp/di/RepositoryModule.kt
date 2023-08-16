package com.example.githubapp.di

import com.example.githubapp.data.repository.search.SearchUserRepository
import com.example.githubapp.data.repository.search.SearchUserRepositoryImp
import com.example.githubapp.data.repository.user.UserRepository
import com.example.githubapp.data.repository.user.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsUsersRepository(repo: UserRepositoryImp): UserRepository

    @Binds
    abstract fun bindsSearchUserRepository(repo: SearchUserRepositoryImp): SearchUserRepository
}
