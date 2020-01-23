package com.example.businessapp.Model.Chart

import com.google.gson.annotations.SerializedName

class ChartResponse {
    @SerializedName("symbol")
    var symbolName : String? = "Brak danych"
    @SerializedName("historical")
    var historical: List<ChartDateResponse>? = listOf()

}

