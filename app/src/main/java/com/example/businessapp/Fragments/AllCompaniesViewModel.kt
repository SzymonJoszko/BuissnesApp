package com.example.businessapp.Fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.businessapp.Model.AllCompanies.AllCompanies
import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse

class AllCompaniesViewModel: ViewModel() {
    private val companies = AllCompanies()
    val allCompaniesList: LiveData<AllCompaniesResponse> = companies.list
}