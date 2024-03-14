package com.rizqanmr.opengallery.di

import com.rizqanmr.opengallery.BuildConfig
import com.rizqanmr.opengallery.network.ApiService
import com.rizqanmr.opengallery.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val retrofitProvider by lazy {
        RetrofitProvider(getOkHttpClient())
    }

    @Provides
    fun provideApiService(): ApiService {
        return retrofitProvider.getApiService()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            addInterceptor(createAuthInterceptor())
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    private fun createAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
                .header("Authorization", BuildConfig.API_KEY)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}