package com.heshmat.graphqltutorial.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heshmat.graphqltutorial.domain.CountryClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val countryClient: CountryClient) :
    ViewModel() {

    private val _state = MutableStateFlow(CountriesStates())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { state ->
                state.copy(
                    countries = countryClient.getCountries().sortedBy { it.name },
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = countryClient.getCountry(code)
                )
            }
        }
    }

    fun dismissCountryDialog(){
        _state.update {
            it.copy(selectedCountry = null)
        }
    }
}