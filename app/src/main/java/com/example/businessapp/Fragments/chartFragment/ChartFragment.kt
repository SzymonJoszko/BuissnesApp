package com.example.businessapp.Fragments.chartFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.businessapp.Model.Chart.MyValueFormatter
import com.example.businessapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_chart.*


class ChartFragment : Fragment() {

    private lateinit var mLineChart: LineChart
    private val PERMISSION_REQUEST_CODE = 1

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

        requestPermission()

        // tworzenie obiektu view model za pomoca view model providers
        val viewModel =
            ViewModelProviders.of(this, ChartViewmodelFactory(arguments!!.getString("symbol")!!))
                .get(ChartViewModel::class.java)

        // obserwowanie Live Daty w view modelu
        viewModel.allChartList.observe(this, Observer { charts ->
            if (charts != null) {
                
                view.findViewById<TextView>(R.id.textViewChartSymbol).text = arguments!!.getString("symbol") +" price history"

                mLineChart = view.findViewById(R.id.testLineChart)
                val entries = ArrayList<Entry>()
                val xValsDateLabel = ArrayList<String>()
                val size = charts.historical!!.size
                var timeInterval = 5  //domyslny przedział czasowy 5 dni
                //sprawdzenie czy istnieje ustawiony interwał czasowy z guzików
                if (arguments!!.getString("interval") != null) {
                    timeInterval = arguments!!.getString("interval")!!.toInt()
                }

                //guziki od dat
                view.findViewById<Button>(R.id.button_chart_5days)
                    .setOnClickListener { SetTimeInterval(5) }
                view.findViewById<Button>(R.id.button_chart_month)
                    .setOnClickListener { SetTimeInterval(30) }
                view.findViewById<Button>(R.id.button_chart_year)
                    .setOnClickListener { SetTimeInterval(365) }
                view.findViewById<Button>(R.id.button_chart_all)
                    .setOnClickListener { SetTimeInterval(size) }

                //zmienna odpowiedzialna za odpowiednia iteracje punktów na wykresie
                var iter = 0


                //petla do dodawania wartosci do wykresu od najmniejszej daty
                for (x in size - timeInterval until size) {
                    var chartDate = charts.historical!![x].date
                    var chartPrice = charts.historical!![x].price
                    //tu zrobilem z tym iter zeby było równo
                    entries.add(Entry(iter++.toFloat(), chartPrice!!.toFloat()))
                    xValsDateLabel.add(chartDate.toString())
                }


                val set1 = LineDataSet(entries, "Prices")
                set1.fillAlpha = 110
                var dataSet = java.util.ArrayList<ILineDataSet>()
                dataSet.add(set1)
                mLineChart.data = LineData(dataSet)

                set1.mode = LineDataSet.Mode.CUBIC_BEZIER
                mLineChart.description.text = " price history"
                mLineChart.legend.isEnabled = false
                mLineChart.invalidate()
                mLineChart.axisRight.isEnabled = false
                mLineChart.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
                mLineChart.axisRight.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)

                val xAxis = mLineChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(true)
                xAxis.labelCount = 4
                xAxis.granularity = 1f
                xAxis.isGranularityEnabled = true
                xAxis.valueFormatter = (MyValueFormatter(xValsDateLabel))


                buttonDownload.setOnClickListener {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            activity!!,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                        mLineChart.saveToGallery(arguments!!.getString("symbol") + "Chart" + timeInterval)
                        Toast.makeText(activity!!, "Chart downloaded!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(activity!!, "Chart didn't download!", Toast.LENGTH_LONG).show()
                    }
                }


            }
        })
    }


    //funkcja odswiezajaca fragment z nowymi danymi
    fun SetTimeInterval(days: Int) {
        val chartFragment = ChartFragment()
        val bundle = Bundle()
        bundle.putString("symbol", arguments!!.getString("symbol"))
        bundle.putString("interval", days.toString())
        chartFragment.arguments = bundle
        val fragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, chartFragment, "ChartFragment").commit()
    }

    private fun requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }

    }

}

