package com.example.businessapp.Model.AllCompanies

import com.google.gson.annotations.SerializedName

class CompanyResponse {
    @SerializedName("symbol")
    var symbol : String? = "Brak danych"
    @SerializedName("name")
    var name: String? = "Brak danych"
    @SerializedName("price")
    var price: String? = "Brak danych"
}