package com.example.businessapp.Model.Details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Details(val symbol : String){
    val details : LiveData<DetailsResponse> = getDetails()

    fun getDetails():MutableLiveData<DetailsResponse>{
        var details :MutableLiveData<DetailsResponse> = MutableLiveData<DetailsResponse>()

        val url = "https://financialmodelingprep.com/api/v3/"
        val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        val services = retrofit.create(IDetailsServices::class.java)
        val call = services.getDetails(symbol)

        call.enqueue(object :Callback<DetailsResponse>{

            override fun onResponse(
                call: Call<DetailsResponse>?,
                response: Response<DetailsResponse>?
            ) {
                details.value = response!!.body()
            }

            override fun onFailure(call: Call<DetailsResponse>?, t: Throwable?) {
            }

        })

        return details

    }
}