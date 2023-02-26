package com.heshmat.graphqltutorial.data.mappers

import com.heshmat.CountriesQuery.Country
import com.heshmat.CountryQuery
import com.heshmat.graphqltutorial.domain.model.DetailedCountry
import com.heshmat.graphqltutorial.domain.model.SimpleCountry

fun Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code,
        name,
        emoji,
        capital ?: "No capital"
    )
}

fun List<Country>.toSimpleCountries() = map {
    it.toSimpleCountry()
}

fun CountryQuery.Country.toDetailedCountry():DetailedCountry {
    return DetailedCountry(
        code,
        name,
        emoji,
        capital ?: "No capital",
        currency ?: "No Currency",
        languages.mapNotNull { it.name },
        continent.name
    )
}