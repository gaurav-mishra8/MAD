package com.example.network.di

import android.content.Context
import android.os.Looper
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "b080301b00b10afb254681c4ee7d6ba8")
                .build()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })

        return httpClient.addInterceptor(logging).build()
    }

    @Provides
    fun provideCache(ctx: Context): Cache {
        return Cache(ctx.cacheDir, 1024L)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: Lazy<OkHttpClient>): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .callFactory {
                client.get().newCall(it)
            }
            .build()
    }

    private fun checkMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw IllegalStateException("HTTP client initialized on main thread.")
        }
    }
}