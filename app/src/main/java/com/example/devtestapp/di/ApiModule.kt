package com.example.devtestapp.di

import android.content.Context
import com.example.devtestapp.data.api.ContactsApi
import com.example.devtestapp.utils.Constants
import com.example.devtestapp.utils.OfflineInterceptor
import com.example.magicapp.utils.OnlineInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun okHttpClient(@ApplicationContext context: Context) : OkHttpClient {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient().newBuilder()
            .addInterceptor(OfflineInterceptor(context))
            .addNetworkInterceptor(OnlineInterceptor())
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideContactApi(@ApplicationContext context: Context): ContactsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient(context))
            .build()
            .create(ContactsApi::class.java)
    }
}