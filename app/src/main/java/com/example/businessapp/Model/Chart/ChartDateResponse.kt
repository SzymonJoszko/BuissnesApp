package com.example.businessapp.Model.Chart

import com.google.gson.annotations.SerializedName

class ChartDateResponse {
    @SerializedName("date")
    var date : String? = "Brak danych"
    @SerializedName("close")
    var price: String? = "Brak danych"

}