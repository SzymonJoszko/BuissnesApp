package com.example.businessapp.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse
import com.example.businessapp.R

class AdapterList(var list: AllCompaniesResponse) : RecyclerView.Adapter<AdapterList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_row, parent, false)
        return ViewHolder(view)
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
    class ViewHolder(val view: View) :RecyclerView.ViewHolder(view){
        val companySymbol = itemView.findViewById<TextView>(R.id.one_symbol_text_view)
        val companyName = itemView.findViewById<TextView>(R.id.one_name_text_view)
    }
}