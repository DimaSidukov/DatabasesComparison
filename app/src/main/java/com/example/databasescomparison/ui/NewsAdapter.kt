package com.example.databasescomparison.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databasescomparison.R
import com.example.databasescomparison.data.model.remotenews.Article

class NewsAdapter(private val newsList: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.text = newsList[position].title
        holder.desc.text = newsList[position].description
        holder.author.text = newsList[position].author
        holder.date.text = newsList[position].publishedAt.take(10)
    }

    override fun getItemCount() = newsList.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val desc: TextView
        val author: TextView
        val date: TextView

        init {
            title = itemView.findViewById(R.id.article_title)
            desc = itemView.findViewById(R.id.article_desc)
            author = itemView.findViewById(R.id.article_author)
            date = itemView.findViewById(R.id.article_date)
        }
    }
}