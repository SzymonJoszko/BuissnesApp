package com.example.businessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.businessapp.Fragments.RecyclerFragment.AllCompaniesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tworzenie obieku fragmentu
        val allCompaniesFragment =
            AllCompaniesFragment()
        // tworzenie fragment managera
        val frgmentManager = supportFragmentManager

        // dodawnaie fragmentu do main activity
        frgmentManager.beginTransaction().add(R.id.fragment_container, allCompaniesFragment).commit()

    }

    // tymczasowe rozwiazanie do wracania
    override fun onBackPressed() {
        //super.onBackPressed()
        // tworzenie obieku fragmentu
        val allCompaniesFragment =
            AllCompaniesFragment()
        // tworzenie fragment managera
        val frgmentManager = supportFragmentManager

        // zamiana fragmentu
        frgmentManager.beginTransaction().replace(R.id.fragment_container, allCompaniesFragment).commit()
    }
}
