package com.example.githubapp.di

import com.example.githubapp.data.api.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    private const val BASE_URL = "https://api.github.com/"
    private const val AUTHORIZATION_HEADER = "Authorization"

    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader(AUTHORIZATION_HEADER, "Bearer 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")
                .build()

            chain.proceed(request)
        }
        .build()

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    fun providesGitHubService(retrofit: Retrofit) = retrofit.create(RemoteDataSource::class.java)
}
