package com.example.zhihuapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.News
import com.example.zhihuapp.R

class NewsAdapter(val newsList: List<News>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val  newsImage:ImageView=view.findViewById(R.id.newsImage)
        val   newsName:TextView=view.findViewById(R.id.newsName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
       val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val news=newsList[position]
            Toast.makeText(parent.context,"you click image${news.name}",Toast.LENGTH_SHORT).show()

        }

        return viewHolder
    }

    override fun getItemCount()=newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val news=newsList[position]
        holder.newsImage.setImageResource(news.imageId)
        holder.newsName.text=news.name
    }
}