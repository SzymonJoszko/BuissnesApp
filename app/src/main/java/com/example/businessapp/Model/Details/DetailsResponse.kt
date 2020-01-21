package com.example.businessapp.Model.Details

import com.google.gson.annotations.SerializedName

class DetailsResponse {
    @SerializedName("symbol")
    var symbol : String = "Brak danych"
    @SerializedName("profile")
    var profile :ProfileResponse = ProfileResponse()
}