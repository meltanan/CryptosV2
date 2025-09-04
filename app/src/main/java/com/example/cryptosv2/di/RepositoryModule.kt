package com.example.cryptosv2.di

import com.example.cryptosv2.data.repositoryImplementation.CryptoRepositoryImpl
import com.example.cryptosv2.domain.repository.CryptoRepository
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
    abstract fun bindCryptoRepository(cryptoRepository: CryptoRepositoryImpl): CryptoRepository
}