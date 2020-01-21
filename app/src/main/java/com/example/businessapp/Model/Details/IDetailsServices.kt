package com.example.businessapp.Model.Details

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IDetailsServices {
    @GET("company/profile/{symbol}")
    fun getDetails(@Path("symbol") symbol:String) : Call<DetailsResponse>
}