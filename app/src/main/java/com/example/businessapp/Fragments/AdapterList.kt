package com.example.businessapp.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse
import com.example.businessapp.R

class AdapterList(var list: AllCompaniesResponse, val onClickElementListener: IOnClickElementListener) : RecyclerView.Adapter<AdapterList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_row, parent, false)
        return ViewHolder(view, onClickElementListener)
    }

    override fun getItemCount(): Int {
        return list.companyResponses!!.size
    }

    // usatwaianie w layoucie wartosci z company
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var company = list.companyResponses!![position]
        holder.companySymbol.text = company!!.symbol
        holder.companyName.text = company!!.name
    }


    // pdzypisywanie elementow z layoutu do holdera
    // ViewHolder dodatkowo implementuje interface zawirajacy onClickListenera
    // dodatkowo ViewHolder zawiera obiekt AllCompaniesFragment za pomoca interaceu...
    // obiekt typu interafacu dzieki czemu mozemy wywolac metode znajdujaca sie w AllCompaniesFragment
    class ViewHolder(val view: View, val onClickElementListener: IOnClickElementListener) :RecyclerView.ViewHolder(view),View.OnClickListener{
        // ustawienie listenera przy inicjalizacji
        init {
            view.setOnClickListener(this)
        }
        // przypisanie ref layoutow
        val companySymbol = itemView.findViewById<TextView>(R.id.one_symbol_text_view)
        val companyName = itemView.findViewById<TextView>(R.id.one_name_text_view)

        // implementacja funkcji z interfaceu
        override fun onClick(v: View?) {
            // wywolanie funkcji znajdujacej sie w AllCompaniesFragment
            onClickElementListener.onElementClick(companySymbol.text.toString())
        }
    }
}