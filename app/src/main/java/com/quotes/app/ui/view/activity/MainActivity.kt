package com.quotes.app.ui.view.activity

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.quotes.app.R
import com.quotes.app.data.api.MainRepository
import com.quotes.app.data.api.RetrofitService
import com.quotes.app.databinding.ActivityMainBinding
import com.quotes.app.ui.view.adapter.QuotesAdapter
import com.quotes.app.ui.viewmodel.MainViewModel
import com.quotes.app.ui.viewmodel.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = QuotesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.quotes)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showHideLoader(true)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerview.adapter = adapter
        viewModel.quotesList.observe(this, Observer {
            if (it != null) {

                showHideLoader(false)
                adapter.setMovieList(it, this@MainActivity)
            } else adapter.clearData()
        })

        viewModel.errorMessage.observe(this, Observer {
            showHideLoader(false)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })


        viewModel.getQuoteList()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return false
            }

        })
        searchView.setOnCloseListener {
            viewModel.getQuoteList()
            false
        }

    }

    private fun search(text: String?) {
        if (!text.isNullOrEmpty()) {
            viewModel.searchQuoteList(text)
        } else viewModel.getQuoteList()
    }

    private fun showHideLoader(isShowing: Boolean) {
        if (isShowing) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.INVISIBLE
        }

    }
}