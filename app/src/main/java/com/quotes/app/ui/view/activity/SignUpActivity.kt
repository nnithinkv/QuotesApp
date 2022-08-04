package com.quotes.app.ui.view.activity

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
import com.quotes.app.data.model.LoginModel
import com.quotes.app.data.model.User
import com.quotes.app.databinding.ActivitySignUpBinding
import com.quotes.app.ui.viewmodel.MainViewModel
import com.quotes.app.ui.viewmodel.MyViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        viewModel.signUpResponse.observe(this) {
            showHideLoader(false)
            val i = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(i)


        }
        binding.loginText.setOnClickListener {
            val i = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(i)
        }
        viewModel.errorMessage.observe(this, Observer {
            showHideLoader(false)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })
        binding.signUpButton.setOnClickListener {
            if (binding.loginField.text.toString().isEmpty()) {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.validation_username),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else if (binding.emailField.text.toString().isEmpty()) {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.validation_email),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else if (binding.passwordField.text.toString().isEmpty()) {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.validation_password),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                showHideLoader(true)
                val user =
                    User(
                        binding.loginField.text.toString(),
                        binding.passwordField.text.toString(),
                        binding.emailField.text.toString()
                    )
                val loginModel = LoginModel(user)
                viewModel.signUpAPI(loginModel)
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
}