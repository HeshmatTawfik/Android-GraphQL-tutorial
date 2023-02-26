package com.heshmat.graphqltutorial.ui.countries

import com.heshmat.graphqltutorial.domain.model.DetailedCountry
import com.heshmat.graphqltutorial.domain.model.SimpleCountry

data class CountriesStates(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading:Boolean = false,
    val selectedCountry:DetailedCountry? = null
)
