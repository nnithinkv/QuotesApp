package com.quotes.app.data.api

import com.quotes.app.data.model.LoginModel
import com.quotes.app.data.model.LoginResponse
import com.quotes.app.data.model.QuotesResponse
import com.quotes.app.data.model.RandomQuote
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("quotes")
    fun getQuoteList(

    ): Call<QuotesResponse>

    @GET("qotd")
    fun getRandomQuote(

    ): Call<RandomQuote>

    @GET("quotes/")
    fun searchQuoteList(
        @Query("filter") filter: String

    ): Call<QuotesResponse>

    @GET("quotes?type=tag")
    fun searchTagQuoteList(
        @Query("filter") filter: String

    ): Call<QuotesResponse>

    @POST("session")
    fun loginAPI(
        @Body data: LoginModel

    ): Call<LoginResponse>

    @POST("users")
    fun signUpAPI(
        @Body data: LoginModel

    ): Call<LoginResponse>

    companion object {

        var retrofitService: RetrofitService? = null

        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


        private val httpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Token token=4c9d78246d809e412acf790034c4da5d")
                    .build()
                chain.proceed(request)
            }.build()

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://favqs.com/api/")

                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}