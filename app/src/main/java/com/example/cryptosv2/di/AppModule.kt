package com.example.cryptosv2.di

import com.example.cryptosv2.data.remote.api.CryptoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideClientAPI(): CryptoAPI {
        return createService(
            CryptoAPI::class.java
        )
    }

    @Singleton
    fun <S> createService(serviceClass: Class<S>): S {
        val baseURL = "https://api.coinpaprika.com/v1/"
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor(interceptor)
        httpClientBuilder.connectTimeout(90, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(90, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClientBuilder.build())
            .build().create(serviceClass)
        return retrofit
    }
}