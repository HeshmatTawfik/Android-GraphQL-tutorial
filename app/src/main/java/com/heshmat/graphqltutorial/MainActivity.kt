package com.heshmat.graphqltutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.heshmat.graphqltutorial.ui.countries.CountriesScreen
import com.heshmat.graphqltutorial.ui.countries.CountriesViewModel
import com.heshmat.graphqltutorial.ui.theme.GraphQLTutorialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLTutorialTheme {
                val viwModel: CountriesViewModel = hiltViewModel()
                val state by viwModel.state.collectAsState()
                Surface(modifier = Modifier.fillMaxSize()) {
                    CountriesScreen(
                        states = state,
                        onSelectedCountry = viwModel::selectCountry,
                        viwModel::dismissCountryDialog
                    )
                }

            }
        }
    }
}