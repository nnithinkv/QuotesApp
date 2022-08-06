package com.quotes.app.ui.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.quotes.app.R
import com.quotes.app.data.model.Quotes
import com.quotes.app.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=getString(R.string.details)
        val quotesData =
            intent?.getSerializableExtra("data") as Quotes?

        binding.title.text = "${'"'}" + quotesData?.body + "${'"'}"
        binding.author.text = "-- " + quotesData?.author
        binding.downVotes.text = quotesData?.downvotesCount.toString()
        binding.upVotes.text = quotesData?.upvotesCount.toString()
        binding.likeCount.text = quotesData?.favoritesCount.toString()
        if (!quotesData?.tags.isNullOrEmpty()) {
            binding.tagContainer.tags = quotesData?.tags
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}