package com.heshmat.graphqltutorial.ui.countries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.heshmat.graphqltutorial.domain.model.DetailedCountry
import com.heshmat.graphqltutorial.domain.model.SimpleCountry

@Composable
fun CountriesScreen(
    states: CountriesStates,
    onSelectedCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxHeight()) {
        if (states.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items (states.countries){ country ->
                    CountryItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectedCountry(country.code) }
                            .padding(16.dp),
                        country = country
                    )
                }
            }

            if (states.selectedCountry !=null){
                CountryDialog(
                    country = states.selectedCountry,
                    onDismiss = onDismissCountryDialog,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .clip(RoundedCornerShape(5.dp))
                        .padding(16.dp)
                )
            }

        }
    }
}

@Composable
private fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = country.emoji,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = country.name,
                    fontSize = 24.sp,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): $joinedLanguages")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CountryItem(modifier:Modifier = Modifier, country: SimpleCountry) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
            .weight(1f)
        ) {
            Text(text = country.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }

}