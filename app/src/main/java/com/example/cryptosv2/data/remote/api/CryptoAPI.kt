package com.example.cryptosv2.data.remote.api

import com.example.cryptosv2.domain.model.Crypto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {
    @GET("coins")
    suspend fun getAllCryptosData(): Response<List<Crypto>>
}