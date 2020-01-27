package com.example.businessapp.Fragments.DetailsFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.businessapp.MainActivity

import com.example.businessapp.R
import com.squareup.picasso.Picasso

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
        val viewModel = ViewModelProviders.of(this, DetailsViewModelFactory(arguments!!.getString("symbol")!!, activity!!.baseContext)).get(DetailsViewModel::class.java)

        // obserwowanie Live Daty w view modelu
        viewModel.details.observe(this, Observer { details ->

            // jesli bedzie jakas zmiana w obserwowanej Libe Dacie to sie wykona
            // jesli details nie jest nullem to wykonaj
            if(details != null){
                view.findViewById<TextView>(R.id.company_name_text_view).text = details.profile.companyName
                // picasso dodany do gardla - do wsadzenia url do imageView
                Picasso.get().load(details.profile.image).into(view.findViewById<ImageView>(R.id.image_view))
                view.findViewById<TextView>(R.id.price_text_view).text = "Price: " + details.profile.price + " " + details.profile.changesProcentage
                view.findViewById<TextView>(R.id.industry_text_view).text = "Industry: " + details.profile.industry
                view.findViewById<TextView>(R.id.sector_text_view).text = "Sector: " + details.profile.sector
                view.findViewById<TextView>(R.id.description_text_view).text = details.profile.description
                view.findViewById<TextView>(R.id.ceo_text_view).text = "Ceo: " + details.profile.ceo
                view.findViewById<TextView>(R.id.website_text_view).text = details.profile.website

                // tymczasowe dodawanie do favorite
                viewModel.addToFavorite(details.symbol, details.profile.companyName, details.profile.price)
            }
            else{
                view.findViewById<TextView>(R.id.company_name_text_view).text = "details = null"
            }
        })

    }

}
