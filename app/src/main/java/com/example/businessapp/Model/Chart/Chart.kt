package com.example.businessapp.Model.Chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse
import com.example.businessapp.Model.AllCompanies.IAllCompaniesServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Chart(val symbol : String) {
    val list: LiveData<ChartResponse> = getAllChartResponse()

    fun getAllChartResponse() : MutableLiveData<ChartResponse> {

        var list : MutableLiveData<ChartResponse> = MutableLiveData<ChartResponse>()

        val url = "https://financialmodelingprep.com/api/v3/"
        val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(IChartServices::class.java)
        val call = service.getAllChartResponse(symbol)

        call.enqueue(object : Callback<ChartResponse> {

            override fun onResponse(
                call: Call<ChartResponse>?,
                response: Response<ChartResponse>?
            ) {
                list.value = response!!.body()
            }

            override fun onFailure(call: Call<ChartResponse>?, t: Throwable?) {
            }

        })

        return list
    }
}