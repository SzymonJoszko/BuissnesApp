package com.example.businessapp.Model.Chart

import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse
import com.example.businessapp.Model.Details.DetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IChartServices {

    @GET("historical-price-full/{symbol}")
    fun getAllChartResponse(@Path("symbol") symbol:String) : Call<ChartResponse>
}