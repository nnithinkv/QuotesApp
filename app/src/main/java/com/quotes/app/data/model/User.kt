package com.quotes.app.data.model

import com.google.gson.annotations.SerializedName


data class User(

    @SerializedName("login") var login: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("email") var email: String? = null

)