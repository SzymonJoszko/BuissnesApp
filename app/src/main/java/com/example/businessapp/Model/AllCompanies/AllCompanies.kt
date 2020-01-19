package com.example.businessapp.Model.AllCompanies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllCompanies {
    val list : LiveData<AllCompaniesResponse> = getAllCompanies()

    fun getAllCompanies() :MutableLiveData<AllCompaniesResponse>{

        var list :MutableLiveData<AllCompaniesResponse> = MutableLiveData<AllCompaniesResponse>()

        val url = "https://financialmodelingprep.com/api/v3/"
        val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(IAllCompaniesSercices::class.java)
        val call = service.getAllCompanies()

        call.enqueue(object : Callback<AllCompaniesResponse>{

            override fun onResponse(
                call: Call<AllCompaniesResponse>?,
                response: Response<AllCompaniesResponse>?
            ) {
                list.value = response!!.body()
            }

            override fun onFailure(call: Call<AllCompaniesResponse>?, t: Throwable?) {
            }

        })

        return list
    }
}