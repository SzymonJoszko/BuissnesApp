package com.example.businessapp.Fragments.RecyclerFragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.businessapp.Fragments.DetailsFragment.DetailsFragment

import com.example.businessapp.R
import kotlinx.android.synthetic.main.fragment_all_companies.*

// klasa implementuje dodatkowo interfejs posiadajacy funkcje opdowiadajaca na zdarzenie onClick
class AllCompaniesFragment : Fragment(),
    IOnClickElementListener {

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
                var adapter =
                    AdapterList(companies, this)
                rec_view.adapter = adapter
            }
        })
    }

    // funkcja wywolujaca sie podczas wykrycia zdarzenia onClick na danym holderze
    override fun onElementClick(symbol: String){
        Log.d("isClicked??", "Clicked" + symbol)

        // tworzenie obiektu fragmentu
        val detailsFragment = DetailsFragment()
        // tworzenie obiektu bundle do przekazania symbolu do details fragmentu
        val bundle = Bundle()
        // dodanie symbolu do bundle
        bundle.putString("symbol", symbol)
        // dodanie bundle do fragmentu
        detailsFragment.arguments = bundle

        // tworzenie fragment managera
        val frgmentManager = activity!!.supportFragmentManager

        // zamiana fragmentow
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, detailsFragment).commit()

    }

}
