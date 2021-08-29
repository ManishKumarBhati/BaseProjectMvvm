package com.citiustech.data.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.citiustech.data.ApiService
import com.citiustech.data.doa.DoaService
import com.citiustech.data.doa.RoomDB
import com.citiustech.data.network.HeaderInterceptor
import com.citiustech.data.network.SSLCertificatePinnerImpl
import com.citiustech.data.util.BASE_URL
import com.citiustech.data.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { Log.d("OkHttp", it) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .certificatePinner(SSLCertificatePinnerImpl.getPinner())
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDoaService(roomDB: RoomDB): DoaService {
        return roomDB.doaService()
    }

}