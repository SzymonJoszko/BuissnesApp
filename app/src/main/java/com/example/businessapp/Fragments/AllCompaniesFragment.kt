package com.example.businessapp.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.businessapp.R
import kotlinx.android.synthetic.main.fragment_all_companies.*

class AllCompaniesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_companies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // tworzenie obiektu view model za pomoca view model providers
        val viewModel = ViewModelProviders.of(this).get(AllCompaniesViewModel::class.java)

        // obserwowanie Live Daty w view modelu
        viewModel.allCompaniesList.observe(this, Observer { companies ->

            // jesli bedzie jakas zmiana w obserwowanej Libe Dacie to sie wykona
            // jesli allCompaniesList nie jest nullem to wykonaj
            if(companies != null){
                // dodawanie elementow do recycler view
                rec_view.layoutManager = LinearLayoutManager(activity!!.baseContext)
                var adapter = AdapterList(companies)
                rec_view.adapter = adapter
            }
        })
    }


}
