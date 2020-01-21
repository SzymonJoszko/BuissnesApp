package com.example.businessapp.Fragments.DetailsFragment

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.businessapp.Model.Details.Details
import com.example.businessapp.Model.Details.DetailsResponse

class DetailsViewModel(val symbol:String): ViewModel() {
    private val detailsObject = Details(symbol)
    val details : LiveData<DetailsResponse> = detailsObject.details
}