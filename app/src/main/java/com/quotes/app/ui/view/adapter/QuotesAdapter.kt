package com.quotes.app.ui.view.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quotes.app.data.model.Quotes
import com.quotes.app.databinding.QuotesItemBinding


class QuotesAdapter : RecyclerView.Adapter<QuotesViewHolder>() {

    private var quotes = mutableListOf<Quotes>()
    private var context: Activity? = null
    fun setMovieList(quotes: List<Quotes>, context: Activity) {
        this.context = context
        this.quotes = quotes.toMutableList()
        notifyDataSetChanged()
    }
    fun clearData()
    {
        this.quotes.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = QuotesItemBinding.inflate(inflater, parent, false)
        return QuotesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quote = quotes[position]
        holder.binding.title.text ="${'"'}"+ quote.body+"${'"'}"
        holder.binding.author.text ="-- "+ quote.author


    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}

class QuotesViewHolder(val binding: QuotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
