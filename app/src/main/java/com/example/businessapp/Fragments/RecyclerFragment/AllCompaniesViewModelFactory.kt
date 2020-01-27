package com.example.businessapp.Fragments.RecyclerFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AllCompaniesViewModelFactory (val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>):T {
        return AllCompaniesViewModel(context) as T
    }
}