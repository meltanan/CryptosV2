package com.example.cryptosv2.presentation.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptosv2.common.ShowProgressIndicator
import com.example.cryptosv2.data.util.UiState
import com.example.cryptosv2.domain.model.Crypto

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val cryptos = viewModel.cryptos.collectAsStateWithLifecycle().value

    @Composable
    fun CryptoCard(crypto: Crypto) {
        Card ( modifier = Modifier.padding(10.dp),
            border = BorderStroke(1.dp, Color.Gray)) {

            Column (
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ){

                Text(crypto.name)
            }
        }
    }

    @Composable
    fun SetupUi(cryptos: List<Crypto>) {

        LazyColumn () {
            items(cryptos) { crypto ->

                CryptoCard(crypto)

            }
        }
    }

    when (cryptos) {
        is UiState.Loading -> {
            Log.d("demo", "1")
            ShowProgressIndicator()
        }

        is UiState.Loaded -> {
            Log.d("demo", "2")
            cryptos.data?.let {
                Log.d("demo", "3")
                SetupUi(it)
            }
        }
        is UiState.Error -> {
            Log.d("demo", "4")
            Text(cryptos.message)
        }
    }

}