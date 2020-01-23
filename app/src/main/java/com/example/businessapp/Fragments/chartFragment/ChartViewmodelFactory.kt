package com.example.businessapp.Fragments.chartFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.businessapp.Fragments.DetailsFragment.DetailsViewModel


class ChartViewmodelFactory(val symbol: String): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>):T {
        return ChartViewModel(symbol) as T
    }
}