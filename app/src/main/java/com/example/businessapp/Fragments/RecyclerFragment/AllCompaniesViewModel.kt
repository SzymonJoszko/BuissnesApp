package com.example.businessapp.Fragments.RecyclerFragment

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.businessapp.MainActivity
import com.example.businessapp.Model.AllCompanies.AllCompanies
import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse
import com.example.businessapp.Model.AllCompanies.CompanyResponse
import com.example.businessapp.Model.Database.DBHelper

class AllCompaniesViewModel(val context:Context): ViewModel() {
    private val companies = AllCompanies()
    private val database = DBHelper(context)
    val allFavoriteList: LiveData<AllCompaniesResponse> = database.allCompanies
    val allCompaniesList: LiveData<AllCompaniesResponse> = companies.list
}