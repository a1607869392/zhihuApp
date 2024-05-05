package com.example.zhihuapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.Banners
import com.example.zhihuapp.R

class BannerAdapter(val bannerList: List<Banners>):RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bannersImage:ImageView=view.findViewById(R.id.image_banner)
        val bannersText:TextView=view.findViewById(R.id.text_banner)
        val bannersWriter:TextView=view.findViewById(R.id.writer_banner)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val banners = bannerList[position]
            Toast.makeText(parent.context, "you clicked view ${banners.text}",
                Toast.LENGTH_SHORT).show()
        }
        viewHolder.bannersImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val banners = bannerList[position]

            Toast.makeText(parent.context, "you clicked image ${banners.text}",
                Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun getItemCount()=bannerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val banners=bannerList[position]
    holder.bannersImage.setImageResource(banners.imageId)
    holder.bannersText.text=banners.text
    holder.bannersWriter.text=banners.writer

    }
}