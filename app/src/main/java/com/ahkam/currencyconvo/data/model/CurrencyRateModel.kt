package com.ahkam.currencyconvo.data.model

import com.google.gson.annotations.SerializedName


data class CurrencyRateModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: Int,
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Rates
)