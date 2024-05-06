package com.example.zhihuapp.Adapter

import android.annotation.SuppressLint
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

class NewsAdapter(var NewsList: List<NewBean.StoriesDTO>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var monItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.monItemClickListener = onItemClickListener
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val  newsImage:ImageView=view.findViewById(R.id.newsImage)
        val   newsName:TextView=view.findViewById(R.id.newsName)
        val   newsWriter:TextView=view.findViewById(R.id.newswriter)
  //      val date:TextView=view.findViewById(R.id.date)
        }
    var mactivity: Activity?=null
   @SuppressLint("NotifyDataSetChanged")
   fun setListData(listdate: List<NewBean.StoriesDTO>){
       this.NewsList=listdate
        //用于通知适配器数据集已更改的方法
        notifyDataSetChanged()}
//    } @SuppressLint("NotifyDataSetChanged")
  fun setActivity(activity: Activity){
       this.mactivity=activity
        //用于通知适配器数据集已更改的方法
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)

       val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            var news= NewsList?.get(position)
            monItemClickListener?.onItemClick(news,position)


        }

        return viewHolder
    }


    override fun getItemCount() = NewsList?.size as Int

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      var news= NewsList?.get(position)
        if (news != null) {
            holder.newsName.text=news.title
        }
        if (news != null) {
            holder.newsWriter.text=news.hint
        }
        //加载图片
        if (news != null) {

              Glide.with(holder.itemView.context).load(news.images.get(0)).into(holder.newsImage)
        }
           }
    interface OnItemClickListener  {
        fun onItemClick(newsdetail: NewBean.StoriesDTO?, position: Int)
    }




}