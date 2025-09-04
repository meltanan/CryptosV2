package com.example.cryptosv2.presentation.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptosv2.common.SearchComponent
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
                    .background(color = Color.Black.copy(alpha = .8f))
                    //.background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ){
                //if (crypto.is_new) {
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(22.dp)
                        .width(42.dp)
                        .background(Color.Red)
                        ) {
                        Text(" New", color = Color.White)
                    }
                //}

                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("${crypto.symbol} | ${crypto.name}", color = Color.White, style = TextStyle(fontWeight = FontWeight.Bold))
                    if (crypto.is_active)
                        Text("Active", color = Color.Green)
                    else
                        Text("Not Active", color = Color.Red)
                }
                Text("Rank ${crypto.rank}", color = Color.White)

            }
        }
    }

    @Composable
    fun SetupUi(cryptos: List<Crypto>) {

        var doSearch by remember() { mutableStateOf(false) }
        var searchText by remember { mutableStateOf("") }

        if (doSearch) {
            doSearch = false
            viewModel.searchCryptos(searchText)
        }

        Column {
            SearchComponent {
                searchText = it
                doSearch = true
            }
            LazyColumn (modifier = Modifier
                .background(Color.Black)
                .padding(top = 5.dp)) {
                items(cryptos) { crypto ->

                    CryptoCard(crypto)

                }
            }
        }

    }

    Box(modifier = Modifier
        .background(color = Color.Black)
        .fillMaxSize())

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