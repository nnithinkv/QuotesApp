package com.quotes.app.data.api

import com.quotes.app.data.model.LoginModel

class MainRepository constructor(private val retrofitService: RetrofitService) {


    fun getQuoteList() =
        retrofitService.getQuoteList()

    fun getRandomQuote() =
        retrofitService.getRandomQuote()

    fun searchQuoteList(filter: String) =
        retrofitService.searchQuoteList(filter)

    fun loginAPI(data: LoginModel) =
        retrofitService.loginAPI(data)

    fun signUpAPI(data: LoginModel) =
        retrofitService.signUpAPI(data)
}