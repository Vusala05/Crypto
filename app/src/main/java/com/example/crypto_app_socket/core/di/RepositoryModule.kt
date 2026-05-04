package com.example.crypto_app_socket.core.di

import com.example.crypto_app_socket.data.repository.DetailRepositoryImpl
import com.example.crypto_app_socket.data.repository.HomeRepositoryImpl
import com.example.crypto_app_socket.domain.repository.DetailRepository
import com.example.crypto_app_socket.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository

}
