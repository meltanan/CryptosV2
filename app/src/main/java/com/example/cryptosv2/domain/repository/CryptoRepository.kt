package com.example.cryptosv2.domain.repository

import com.example.cryptosv2.data.util.Resource
import com.example.cryptosv2.domain.model.Crypto

interface CryptoRepository {
    suspend fun getAllCryptosData(): Resource<List<Crypto>>
}