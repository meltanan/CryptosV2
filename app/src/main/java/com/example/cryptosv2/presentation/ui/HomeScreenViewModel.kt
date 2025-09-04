package com.example.cryptosv2.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cryptosv2.data.util.Resource
import com.example.cryptosv2.data.util.UiState
import com.example.cryptosv2.domain.model.Crypto
import com.example.cryptosv2.domain.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val cryptoRepo: CryptoRepository):
    ViewModel() {

    private val _cryptos = MutableStateFlow<UiState<List<Crypto>>>(UiState.Loading())
    val cryptos = _cryptos

    init {
        viewModelScope.launch {
            getCryptos()
        }
    }

    suspend fun getCryptos() {

        val list = listOf<Crypto>(
            Crypto(
                id = "1",
                name = "Bit Coint",
                symbol = "BTC",
                rank = 1,
                is_new = true,
                is_active = true,
                type = "coin"
            ),
            Crypto(
                id = "1",
                name = "Bit Coint",
                symbol = "BTC",
                rank = 1,
                is_new = true,
                is_active = true,
                type = "coin"
            ),
                    Crypto(
                    id = "1",
            name = "Bit Coint",
            symbol = "BTC",
            rank = 1,
            is_new = false,
            is_active = false,
            type = "coin"
        ),
        Crypto(
            id = "1",
            name = "Bit Coint",
            symbol = "BTC",
            rank = 1,
            is_new = true,
            is_active = true,
            type = "coin"
        )
        )

        _cryptos.emit(UiState.Loaded(list))
//        when (val response = cryptoRepo.getAllCryptosData()) {
//            is Resource.Success -> {
//                response.data?.let {
//                    _cryptos.emit(UiState.Loaded(it))
//                } ?: run {
//                    _cryptos.emit(UiState.Error(response.uiErrorMessage))
//                }
//            }
//
//            else -> {
//                _cryptos.emit(UiState.Error(response.uiErrorMessage))
//            }
//        }
    }
}