package com.example.data.di

import com.example.data.api.MangoTestBackendApiService
import com.example.data.repository.MangoTestBackendAuthRepository
import com.example.domain.auth.AuthRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        mangoTestBackendApiService: MangoTestBackendApiService,
    ): AuthRepository = MangoTestBackendAuthRepository(
        mangoTestBackendApiService,
    )

    @Singleton
    @Provides
    fun provideClientService(retrofit: Retrofit): MangoTestBackendApiService =
        retrofit.create(MangoTestBackendApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .create()

        return Retrofit.Builder()
            .baseUrl(MangoTestBackendApiService.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient
            .Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLoggingInterceptor)

        return builder.build()
    }
}