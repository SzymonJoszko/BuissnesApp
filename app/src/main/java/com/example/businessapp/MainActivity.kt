package com.example.businessapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.businessapp.Fragments.DetailsFragment.DetailsFragment
import com.example.businessapp.Fragments.RecyclerFragment.AllCompaniesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tagAllCompaniesFragment :String = "ALL_COMPANIES_FRAGMENT"
    private var currentFragment :String = tagAllCompaniesFragment
    private val currentTagFragmentKey :String = "FRAGMENT_TAG"
    private var isFavorite :Boolean = false

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
            if(currentFragment == tagAllCompaniesFragment){
                isFavorite = savedInstanceState.getBoolean("favorite")
            }
        }

        // dodawnaie obecnego fragmentu do main activity
        if(currentFragment == tagAllCompaniesFragment) {
            // tworzenie obiektu bundle do przekazania symbolu do details fragmentu
            val bundle = Bundle()
            // dodanie symbolu do bundle
            bundle.putBoolean("favorite", isFavorite)
            // dodanie bundle do fragmentu
            allCompaniesFragment.arguments = bundle
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

        if(currentFragment == tagAllCompaniesFragment){
            outState.putBoolean("favorite", isFavorite)
        }
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
        // tworzenie obiektu bundle do przekazania symbolu do details fragmentu
        val bundle = Bundle()
        // dodanie symbolu do bundle
        isFavorite = false
        bundle.putBoolean("favorite", false)
        // dodanie bundle do fragmentu
        allCompaniesFragment.arguments = bundle

        //zadanie egzamin ciezkie
        val detailsFragment = DetailsFragment()
        val bundle1 = Bundle()

        val fragmentCharts = frgmentManager.findFragmentByTag("ChartFragment")
        detailsFragment.arguments = bundle1

        if(fragmentCharts != null && fragmentCharts.isVisible){
            bundle1.putString("symbol",fragmentCharts!!.arguments!!.getString("symbol"))
            frgmentManager.beginTransaction().replace(R.id.fragment_container, detailsFragment).commit()
        }
        else
        {
            // zamiana fragmentu
            frgmentManager.beginTransaction().replace(R.id.fragment_container, allCompaniesFragment).commit()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id_menu = item.itemId
        if(id_menu == R.id.favorite_menu_item){

            // tworzenie obieku fragmentu
            val allCompaniesFragment =
                AllCompaniesFragment()
            // tworzenie fragment managera
            val frgmentManager = supportFragmentManager

            currentFragment = tagAllCompaniesFragment

            // tworzenie obiektu bundle do przekazania symbolu do details fragmentu
            val bundle = Bundle()
            // dodanie symbolu do bundle
            isFavorite = true
            bundle.putBoolean("favorite", true)
            // dodanie bundle do fragmentu
            allCompaniesFragment.arguments = bundle

            frgmentManager.beginTransaction().replace(R.id.fragment_container, allCompaniesFragment, tagAllCompaniesFragment).commit()

            Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}
