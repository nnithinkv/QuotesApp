package com.quotes.app.data.model

import com.google.gson.annotations.SerializedName


data class RandomQuote(

    @SerializedName("qotd_date") var qotdDate: String? = null,
    @SerializedName("quote") var quote: Quotes? = Quotes()

)