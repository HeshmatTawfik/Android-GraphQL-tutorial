package com.heshmat.graphqltutorial.di

import com.heshmat.graphqltutorial.data.ApolloCountryClient
import com.heshmat.graphqltutorial.domain.CountryClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Repository {
    @Binds
    @Singleton
    abstract fun bindCountryClient(apolloCountryClient: ApolloCountryClient):CountryClient
}