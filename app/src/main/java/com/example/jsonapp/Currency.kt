package com.example.jsonapp

import com.google.gson.annotations.SerializedName

class Currency {

    @SerializedName("date")
    var date: String? = null

    @SerializedName("eur")
    var eur: Cur? = null

    class Cur{

        @SerializedName("ada")
        var ada: Float? = null

        @SerializedName("aed")
        var aed: Float? = null

        @SerializedName("afn")
        var afn: Float? = null

        @SerializedName("all")
        var all: Float? = null

        @SerializedName("amd")
        var amd: Float? = null

        @SerializedName("ang")
        var ang: Float? = null

        @SerializedName("aoa")
        var aoa: Float? = null

        @SerializedName("ars")
        var ars: Float? = null
    }
}