package com.quotes.app.data.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(

    @SerializedName("User-Token") var token: String? = null,
    @SerializedName("login") var login: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("error_code") var errorCode: Int? = null,
    @SerializedName("message") var message: String? = null

)