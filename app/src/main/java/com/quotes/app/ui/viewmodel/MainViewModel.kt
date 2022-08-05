package com.quotes.app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quotes.app.data.api.MainRepository
import com.quotes.app.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {


    val quotesList = MutableLiveData<List<Quotes>>()
    val randomQuote = MutableLiveData<Quotes>()
    val loginResponse = MutableLiveData<LoginResponse>()
    val signUpResponse = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()


    fun getRandomQuote() {

        val response = repository.getRandomQuote()
        response.enqueue(object : Callback<RandomQuote> {
            override fun onResponse(
                call: Call<RandomQuote>,
                response: Response<RandomQuote>
            ) {

                if (response.isSuccessful) {
                    val apiResult = response.body()
                    randomQuote.postValue(apiResult?.quote)
                } else errorMessage.postValue("Something went wrong!!")

            }

            override fun onFailure(call: Call<RandomQuote>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        }
        )
    }

    fun getQuoteList() {

        val response = repository.getQuoteList()
        response.enqueue(object : Callback<QuotesResponse> {
            override fun onResponse(
                call: Call<QuotesResponse>,
                response: Response<QuotesResponse>
            ) {

                if (response.isSuccessful) {
                    val apiResult = response.body()
                    quotesList.postValue(apiResult?.quotes)
                } else errorMessage.postValue("Something went wrong!!")

            }

            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        }
        )
    }

    fun searchQuoteList(filter: String) {

        val response = repository.searchQuoteList(filter)
        response.enqueue(object : Callback<QuotesResponse> {
            override fun onResponse(
                call: Call<QuotesResponse>,
                response: Response<QuotesResponse>
            ) {

                if (response.isSuccessful) {
                    val apiResult = response.body()
                    if (apiResult?.quotes?.size == 1 && apiResult.quotes[0].id == 0) {
                        quotesList.postValue(null)
                        errorMessage.postValue("No data found!!")
                    } else quotesList.postValue(apiResult?.quotes)
                } else errorMessage.postValue("Something went wrong!!")

            }

            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        }
        )
    }
    fun searchTagQuoteList(filter: String) {

        val response = repository.searchTagQuoteList(filter)
        response.enqueue(object : Callback<QuotesResponse> {
            override fun onResponse(
                call: Call<QuotesResponse>,
                response: Response<QuotesResponse>
            ) {

                if (response.isSuccessful) {
                    val apiResult = response.body()
                    if (apiResult?.quotes?.size == 1 && apiResult.quotes[0].id == 0) {
                        quotesList.postValue(null)
                        errorMessage.postValue("No data found!!")
                    } else quotesList.postValue(apiResult?.quotes)
                } else errorMessage.postValue("Something went wrong!!")

            }

            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        }
        )
    }

    fun loginAPI(data: LoginModel) {

        val response = repository.loginAPI(data)
        response.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {

                if (response.isSuccessful) {
                    val apiResult = response.body()

                    if (!apiResult?.message.isNullOrEmpty()) {

                        errorMessage.postValue(apiResult?.message)
                    } else loginResponse.postValue(apiResult)


                } else errorMessage.postValue("Something went wrong!!")

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        }
        )
    }

    fun signUpAPI(data: LoginModel) {

        val response = repository.signUpAPI(data)
        response.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {

                if (response.isSuccessful) {
                    val apiResult = response.body()

                    if (!apiResult?.message.isNullOrEmpty()) {

                        errorMessage.postValue(apiResult?.message)
                    } else signUpResponse.postValue(apiResult)


                } else errorMessage.postValue("Something went wrong!!")

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        }
        )
    }
}