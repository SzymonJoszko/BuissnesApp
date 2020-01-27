package com.example.businessapp.Fragments.chartFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.businessapp.Model.Chart.Chart
import com.example.businessapp.Model.Chart.ChartResponse

class ChartViewModel(val symbol:String) : ViewModel() {
    private val charts = Chart(symbol)
    val allChartList: LiveData<ChartResponse> = charts.list
}
