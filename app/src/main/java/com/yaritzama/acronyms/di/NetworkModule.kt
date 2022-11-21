package com.yaritzama.acronyms.di

import androidx.viewbinding.BuildConfig
import com.yaritzama.acronyms.data.api.AcronymAPI
import com.yaritzama.acronyms.domain.repository.RepositoryAcronym
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
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
        if(BuildConfig.DEBUG){
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://www.nactem.ac.uk/software/acromine/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesAcronymMeaningAPI(retrofit: Retrofit): AcronymAPI =
        retrofit.create(AcronymAPI::class.java)
}