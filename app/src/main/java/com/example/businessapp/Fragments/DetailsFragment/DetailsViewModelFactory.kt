package com.example.businessapp.Fragments.DetailsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsViewModelFactory(val symbol: String): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>):T {
        return DetailsViewModel(symbol) as T
    }
}