package com.example.businessapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.businessapp.Fragments.RecyclerFragment.AllCompaniesFragment

class MainActivity : AppCompatActivity() {

    private val tagAllCompaniesFragment :String = "ALL_COMPANIES_FRAGMENT"
    private var currentFragment :String = tagAllCompaniesFragment
    private val currentTagFragmentKey :String = "FRAGMENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tworzenie obieku fragmentu
        val allCompaniesFragment =
            AllCompaniesFragment()
        // tworzenie fragment managera
        val frgmentManager = supportFragmentManager

        // przypisywanie obecnego fragmentu
        if(savedInstanceState != null){
            currentFragment = savedInstanceState.getString(currentTagFragmentKey)!!
        }

        // dodawnaie obecnego fragmentu do main activity
        if(currentFragment == tagAllCompaniesFragment) {
            frgmentManager.beginTransaction()
                .replace(R.id.fragment_container, allCompaniesFragment, currentFragment).commit()
        }else{
            frgmentManager.beginTransaction().replace(R.id.fragment_container, frgmentManager.findFragmentByTag(currentFragment) as Fragment, currentFragment).commit()
        }
    }

    // setter currentFragmentu
    fun setMyCurrentFragment(tag:String){
        currentFragment = tag
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(currentTagFragmentKey, currentFragment)
    }


    // tymczasowe rozwiazanie do wracania
    override fun onBackPressed() {
        //super.onBackPressed()
        // tworzenie obieku fragmentu
        val allCompaniesFragment =
            AllCompaniesFragment()
        // tworzenie fragment managera
        val frgmentManager = supportFragmentManager
        // ustawianei obecnego fragmentu
        setMyCurrentFragment(tagAllCompaniesFragment)

        // zamiana fragmentu
        frgmentManager.beginTransaction().replace(R.id.fragment_container, allCompaniesFragment).commit()
    }
}
