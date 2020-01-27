package com.example.businessapp.Fragments.DetailsFragment

import android.content.Context
import android.telecom.Call
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.businessapp.Model.AllCompanies.CompanyResponse
import com.example.businessapp.Model.Database.DBHelper
import com.example.businessapp.Model.Details.Details
import com.example.businessapp.Model.Details.DetailsResponse

class DetailsViewModel(val symbol:String, val context: Context): ViewModel() {
    private val detailsObject = Details(symbol)
    private val databse = DBHelper(context)
    val details : LiveData<DetailsResponse> = detailsObject.details

    fun addToFavorite(symbol: String, name: String, price: String){
        var company = CompanyResponse()
        company.symbol = symbol
        company.name = name
        company.price = price

        databse.addCompany(company)

        Toast.makeText(context, "Company Added to Favorite", Toast.LENGTH_LONG)
    }
}