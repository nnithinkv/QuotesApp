package com.quotes.app.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.quotes.app.R
import com.quotes.app.data.api.MainRepository
import com.quotes.app.data.api.RetrofitService
import com.quotes.app.data.model.LoginModel
import com.quotes.app.data.model.User
import com.quotes.app.databinding.ActivityLoginBinding
import com.quotes.app.ui.viewmodel.MainViewModel
import com.quotes.app.ui.viewmodel.MyViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=getString(R.string.login)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        viewModel.loginResponse.observe(this, Observer {
            showHideLoader(false)
            Toast.makeText(
                this@LoginActivity,
                getString(R.string.successfully_login),
                Toast.LENGTH_LONG
            ).show()
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()


        })

        viewModel.errorMessage.observe(this, Observer {
            showHideLoader(false)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })
        binding.createAccountText.setOnClickListener {
            val i = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(i)
        }
        binding.loginButton.setOnClickListener {
            if (binding.loginField.text.toString().isNullOrEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.validation_username_email),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else if (binding.passwordField.text.toString().isNullOrEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.validation_password),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                showHideLoader(true)
                val user =
                    User(binding.loginField.text.toString(), binding.passwordField.text.toString())
                val loginModel = LoginModel(user)
                viewModel.loginAPI(loginModel)
            }
        }
    }

    private fun showHideLoader(isShowing: Boolean) {
        if (isShowing) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.INVISIBLE
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}