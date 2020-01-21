package com.example.businessapp.Fragments.DetailsFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.businessapp.R

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tworzenie view modelu
        val viewModel = ViewModelProviders.of(this, DetailsViewModelFactory(arguments!!.getString("symbol")!!)).get(DetailsViewModel::class.java)

        // obserwowanie Live Daty w view modelu
        viewModel.details.observe(this, Observer { details ->

            // jesli bedzie jakas zmiana w obserwowanej Libe Dacie to sie wykona
            // jesli details nie jest nullem to wykonaj
            if(details != null){
                view.findViewById<TextView>(R.id.testText).text = details.profile.companyName
            }
            else{
                view.findViewById<TextView>(R.id.testText).text = "details = null"
            }
        })

    }

}
