package com.yaritzama.acronyms.di

import com.yaritzama.acronyms.data.api.AcronymAPI
import com.yaritzama.acronyms.data.repository.RepositoryAcronymImpl
import com.yaritzama.acronyms.domain.repository.RepositoryAcronym
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(acronymAPI: AcronymAPI):
            RepositoryAcronym = RepositoryAcronymImpl(acronymAPI)
}