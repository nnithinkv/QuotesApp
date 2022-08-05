package com.quotes.app.ui.view.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quotes.app.R
import com.quotes.app.data.model.Quotes
import com.quotes.app.databinding.ActivityDetailsBinding
import com.quotes.app.databinding.ActivityIntroBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val quotesData = if (Build.VERSION.SDK_INT >= 33) {
            intent?.getSerializableExtra("data", Quotes::class.java)
        } else {
            intent?.getSerializableExtra("data")as Quotes
        }
       binding.title.text ="${'"'}"+ quotesData?.body+"${'"'}"
       binding.author.text ="-- "+ quotesData?.author
        if (!quotesData?.tags.isNullOrEmpty()) {
            binding.tagContainer.tags = quotesData?.tags
        }
    }
}