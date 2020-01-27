package com.example.businessapp.Model.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.businessapp.Model.AllCompanies.AllCompaniesResponse
import com.example.businessapp.Model.AllCompanies.CompanyResponse
import java.time.LocalDate

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER){
    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "FavoriteCompanies.db"

        //Table
        private val TABLE_NAME = "Favorite"
        private val COL_SYMBOL = "Symbol"
        private val COL_NAME = "Name"
        private val COL_PRICE = "Price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_SYMBOL TEXT PRIMARY KEY, $COL_NAME TEXT, $COL_PRICE TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    val allCompanies: LiveData<AllCompaniesResponse>
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            val companiesList = MutableLiveData<AllCompaniesResponse>()
            val companies = mutableListOf<CompanyResponse>()
            val SELECT_QUERY = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(SELECT_QUERY, null)

            if(cursor.moveToFirst()){
                do{
                    val company = CompanyResponse()
                    company.symbol = cursor.getString(cursor.getColumnIndex(COL_SYMBOL))
                    company.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    company.price = cursor.getString(cursor.getColumnIndex(COL_PRICE))

                    companies.add(company)
                }while(cursor.moveToNext())
                var allCompaniesResponse = AllCompaniesResponse()
                allCompaniesResponse.companyResponses = companies
                companiesList.value = allCompaniesResponse
            }
            db.close()
            return companiesList
        }

    fun addCompany(company: CompanyResponse){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_SYMBOL, company.symbol)
        values.put(COL_NAME, company.name)
        values.put(COL_PRICE, company.price)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun editCompany(company: CompanyResponse){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_SYMBOL, company.symbol)
        values.put(COL_NAME, company.name)
        values.put(COL_PRICE, company.price)

        db.update(TABLE_NAME, values, "$COL_SYMBOL=?", arrayOf(company.symbol))
        db.close()
    }

    fun deleteCompany(symbol:String){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_SYMBOL=?", arrayOf(symbol))
        db.close()
    }
}