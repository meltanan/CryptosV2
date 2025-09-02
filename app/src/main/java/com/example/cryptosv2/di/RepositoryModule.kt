package com.example.cryptosv2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun bindClientRepository(clientRepositoryImpl: ClientRepositoryImpl): ClientRepository
}