package com.example.businessapp.Fragments.chartFragment

import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.businessapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class chartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)

        var chart: LineChart? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tworzenie obiektu view model za pomoca view model providers
        val viewModel =
            ViewModelProviders.of(this, ChartViewmodelFactory(arguments!!.getString("symbol")!!))
                .get(ChartViewModel::class.java)

        // obserwowanie Live Daty w view modelu
        viewModel.allChartList.observe(this, Observer { charts ->
            if (charts != null) {
                val entries = ArrayList<Entry>()
                val size = charts.historical!!.size
                var timeInterval = 5
                if (arguments!!.getString("interval") != null){
                    timeInterval = arguments!!.getString("interval")!!.toInt()
                }

                //guziki od dat
                view.findViewById<Button>(R.id.button_chart_5days).setOnClickListener { SetTimeInterval(5) }
                view.findViewById<Button>(R.id.button_chart_month).setOnClickListener {SetTimeInterval(30)}
                view.findViewById<Button>(R.id.button_chart_year).setOnClickListener {SetTimeInterval(365)}
                view.findViewById<Button>(R.id.button_chart_all).setOnClickListener {SetTimeInterval(size)}

                // policzyc wszystkie elementy
                //zrobic petle chwytajaca ostatnie (ilosc)
                //dodac entry na tej podstawie
                //~wiki


                //petla od size to ostatnia data
                for (x in size - timeInterval until size) {
                    var chartDate = charts.historical!![x].date
                    var chartPrice = charts.historical!![x].price

                    //przekształcenie daty na timestamp //na razie nie uzyte bo problem z poisem osi x
                    val l = LocalDate.parse(chartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    val unix = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
                    Log.i(tag, "to unix: " + unix.toString())

                    entries.add(Entry(x.toFloat(), chartPrice!!.toFloat()))
                }

                //dane do wykresu
                val dataSet = LineDataSet(entries, "Test")
                val lineData = LineData(dataSet)
                view.findViewById<LineChart>(R.id.testLineChart).data = lineData
                view.findViewById<LineChart>(R.id.testLineChart).invalidate()

            }
        })

    }


    //funkcja odswiezajaca fragment z nowymi danymi
    //problem z funkcja jest taki ze bierze np 365 ostatnich wpisów a nie 365 ostatnich dni, moze sie uda jeszcze przerobic i wogole to wrzucic to do modelu
    fun SetTimeInterval(days:Int){
        val chartFragment = chartFragment()
        val bundle = Bundle()
        bundle.putString("symbol", arguments!!.getString("symbol"))
        bundle.putString("interval", days.toString())
        chartFragment.arguments = bundle
        val fragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, chartFragment).commit()
    }




}
