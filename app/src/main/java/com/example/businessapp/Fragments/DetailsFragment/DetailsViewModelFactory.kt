package com.example.businessapp.Fragments.DetailsFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsViewModelFactory(val symbol: String, val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>):T {
        return DetailsViewModel(symbol, context) as T
    }
}