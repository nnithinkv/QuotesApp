package com.quotes.app.data.model

import com.google.gson.annotations.SerializedName


data class LoginModel(

    @SerializedName("user") var user: User? = User()

)