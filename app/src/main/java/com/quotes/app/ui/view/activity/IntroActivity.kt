package com.quotes.app.ui.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.quotes.app.R
import com.quotes.app.data.api.MainRepository
import com.quotes.app.data.api.RetrofitService
import com.quotes.app.databinding.ActivityIntroBinding
import com.quotes.app.ui.viewmodel.MainViewModel
import com.quotes.app.ui.viewmodel.MyViewModelFactory

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showHideLoader(true)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )


        viewModel.randomQuote.observe(this, Observer {


            showHideLoader(false)
            binding.title.text = "${'"'}" + it.body + "${'"'}"
            binding.price.text = "-- " + it.author

        })

        viewModel.errorMessage.observe(this, Observer {
            showHideLoader(false)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })
        binding.createAccountText.setOnClickListener {
            val i = Intent(this@IntroActivity, SignUpActivity::class.java)
            startActivity(i)
        }
        binding.loginText.setOnClickListener {
            val i = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(i)
        }
        binding.startText.setOnClickListener {
            val i = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(i)
        }
        viewModel.getRandomQuote()
    }

    private fun showHideLoader(isShowing: Boolean) {
        if (isShowing) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.INVISIBLE
        }

    }
}