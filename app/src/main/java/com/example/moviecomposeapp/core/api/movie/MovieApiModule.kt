package com.example.moviecomposeapp.core.api.movie

import com.example.moviecomposeapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieApiModule {

    @Provides
    internal fun provideBaseUrl() = MovieApiConfig.BASE_URL

    @Provides
    @Singleton
    internal fun provideTokenInterceptor(): Interceptor = Interceptor {
        var original = it.request()
        val url =
            original.url.newBuilder().addQueryParameter("api_key", MovieApiConfig.API_KEY)
                .build()
        original = original.newBuilder().url(url).build()
        it.proceed(original)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(
        tokenInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }
        return client.build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)
}