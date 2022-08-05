package com.quotes.app.ui.view.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.lujun.androidtagview.TagView
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

    var onItemClick: ((Quotes) -> Unit)? = null
    var onClickTag: ((String?) -> Unit)? = null
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
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(quote)
        }
        if (quote.tags.isNotEmpty()){
            holder.binding.tagContainer.tags = quote.tags

            holder.binding.tagContainer.setOnTagClickListener(object :TagView.OnTagClickListener{
                override fun onTagClick(position: Int, text: String?) {
                    onClickTag?.invoke(text)
                }

                override fun onTagLongClick(position: Int, text: String?) {

                }

                override fun onSelectedTagDrag(position: Int, text: String?) {
                    TODO("Not yet implemented")
                }

                override fun onTagCrossClick(position: Int) {
                    TODO("Not yet implemented")
                }

            }
            )
        }




    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}

class QuotesViewHolder(val binding: QuotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
interface ItemClickListener {
    fun onClick(data:Quotes)
    fun onClickTag(data:String)
}
