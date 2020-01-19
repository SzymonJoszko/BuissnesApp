package com.example.businessapp.Model.AllCompanies

import retrofit2.Call
import retrofit2.http.GET

interface IAllCompaniesSercices {
    @GET("company/stock/list")
    fun getAllCompanies(): Call<AllCompaniesResponse>
}