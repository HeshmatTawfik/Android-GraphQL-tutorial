package com.heshmat.graphqltutorial.domain

import com.heshmat.graphqltutorial.domain.model.DetailedCountry
import com.heshmat.graphqltutorial.domain.model.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}