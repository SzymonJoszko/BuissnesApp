package com.example.businessapp.Model.AllCompanies

import com.google.gson.annotations.SerializedName

class AllCompaniesResponse {
    @SerializedName("symbolsList")
    var companyResponses: List<CompanyResponse>? = listOf()
}