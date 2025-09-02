package com.example.cryptosv2.common

enum class ApiResponseErrors(val errorCode: Int, val errorMessage: String) {
    TIMEOUT_EXCEPTION(0,"Timeout error")
}