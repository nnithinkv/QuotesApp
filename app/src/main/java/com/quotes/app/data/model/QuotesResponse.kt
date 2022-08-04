package com.quotes.app.data.model

import com.google.gson.annotations.SerializedName


data class QuotesResponse(

    @SerializedName("page") var page: Int? = null,
    @SerializedName("last_page") var lastPage: Boolean? = null,
    @SerializedName("quotes") var quotes: ArrayList<Quotes> = arrayListOf()

)