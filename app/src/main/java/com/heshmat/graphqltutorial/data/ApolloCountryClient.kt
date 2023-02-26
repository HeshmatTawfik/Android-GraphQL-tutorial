package com.heshmat.graphqltutorial.data

import com.apollographql.apollo3.ApolloClient
import com.heshmat.CountriesQuery
import com.heshmat.CountryQuery
import com.heshmat.graphqltutorial.data.mappers.toDetailedCountry
import com.heshmat.graphqltutorial.data.mappers.toSimpleCountries
import com.heshmat.graphqltutorial.domain.CountryClient
import com.heshmat.graphqltutorial.domain.model.DetailedCountry
import com.heshmat.graphqltutorial.domain.model.SimpleCountry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApolloCountryClient @Inject constructor(private val apolloClient: ApolloClient):CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
      return apolloClient
          .query(CountriesQuery())
          .execute()
          .data
          ?.countries
          ?.toSimpleCountries() ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }

}