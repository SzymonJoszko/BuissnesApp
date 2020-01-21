package com.example.businessapp.Model.Details

import com.google.gson.annotations.SerializedName

class ProfileResponse {
    @SerializedName("companyName")
    var companyName :String = "Brak danych"
    @SerializedName("image")
    var image :String = "https://image.shutterstock.com/image-vector/data-not-found-icon-260nw-1379987435.jpg"
    @SerializedName("price")
    var price :String = "Brak danych"
    @SerializedName("changesPercentage")
    var changesProcentage :String = "Brak danych"
    @SerializedName("changes")
    var changesValue :String = "Brak danych"
    @SerializedName("exchange")
    var exchange :String = "Brak danych"
    @SerializedName("industry")
    var industry :String = "Brak danych"
    @SerializedName("website")
    var website :String = "Brak danych"
    @SerializedName("description")
    var description :String = "Brak danych"
    @SerializedName("ceo")
    var ceo :String = "Brak danych"
    @SerializedName("sector")
    var sector :String = "Brak danych"
}