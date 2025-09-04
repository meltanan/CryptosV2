package com.example.cryptosv2.data.repositoryImplementation

import com.example.cryptosv2.data.remote.api.CryptoAPI
import com.example.cryptosv2.data.remote.network.APISHelpers
import com.example.cryptosv2.data.util.Resource
import com.example.cryptosv2.domain.model.Crypto
import com.example.cryptosv2.domain.repository.CryptoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepositoryImpl @Inject constructor(
    private val api: CryptoAPI,
    private val apiHelpers: APISHelpers
) : CryptoRepository {
    override suspend fun getAllCryptosData(): Resource<List<Crypto>> {
        return apiHelpers.callApi { api.getAllCryptosData() }
    }
}