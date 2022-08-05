package com.quotes.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Quotes(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("dialogue") var dialogue: Boolean? = null,
    @SerializedName("private") var private: Boolean? = null,
    @SerializedName("tags") var tags: ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url: String? = null,
    @SerializedName("favorites_count") var favoritesCount: Int? = null,
    @SerializedName("upvotes_count") var upvotesCount: Int? = null,
    @SerializedName("downvotes_count") var downvotesCount: Int? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("author_permalink") var authorPermalink: String? = null,
    @SerializedName("body") var body: String? = null

) : Serializable