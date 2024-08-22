package com.example.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.api.MangoTestBackendApiService
import com.example.data.data_source.DataStoreSource
import com.example.data.repository.MangoTestBackendAuthRepository
import com.example.data.repository.PreferencesDataStoreUserPreferencesRepository
import com.example.domain.auth.AuthRepository
import com.example.domain.user_preferences.UserPreferencesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

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

    @Singleton
    @Provides
    fun provideUserSettingsRepository(
        dataStorePreferences: DataStore<Preferences>
    ) : UserPreferencesRepository {
        return PreferencesDataStoreUserPreferencesRepository(DataStoreSource(dataStorePreferences))
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}