package com.example.zhihuapp.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zhihuapp.Bean.NewBean
import com.example.zhihuapp.R

class BannerAdapter(var bannerList: List<NewBean.TopStoriesDTO>):RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    private var monItemClickListener2: TopOnItemClickListener2? = null
    fun setOnItemClickListener2(onItemClickListener2: TopOnItemClickListener2) {
        this.monItemClickListener2 = onItemClickListener2
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bannersImage:ImageView=view.findViewById(R.id.image_banner)
        val bannersText:TextView=view.findViewById(R.id.text_banner)
        val bannersWriter:TextView=view.findViewById(R.id.writer_banner)

    }
    var mactivity: Activity?=null
    fun setListData(listdate: List<NewBean.TopStoriesDTO>){
        this.bannerList=listdate
        //用于通知适配器数据集已更改的方法
        notifyDataSetChanged()}

    fun setActivity(activity: Activity){
        this.mactivity=activity
        //用于通知适配器数据集已更改的方法
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val banners = bannerList?.get(position)
            monItemClickListener2?.toponItemClick2(banners,position)
        }

        return viewHolder
    }

    override fun getItemCount()=bannerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val banners=bannerList?.get(position)
    //holder.bannersImage.setImageResource(banners.imageId)
        if (banners != null) {
            holder.bannersText.text=banners.title
        }
        if (banners != null) {
            holder.bannersWriter.text=banners.hint
        }
        if (banners!=null){
             Glide.with(holder.itemView.context).load(banners.image).into(holder.bannersImage)
        }

    }
    interface TopOnItemClickListener2 {
        fun toponItemClick2(newsdetail: NewBean.TopStoriesDTO?, position: Int)
    }

}