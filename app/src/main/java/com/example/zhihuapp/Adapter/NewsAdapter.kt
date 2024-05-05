package com.example.zhihuapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
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
    lateinit var mContext: Context
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val  newsImage:ImageView=view.findViewById(R.id.newsImage)
        val   newsName:TextView=view.findViewById(R.id.newsName)
        val   newsWriter:TextView=view.findViewById(R.id.newswriter)
        val date:TextView=view.findViewById(R.id.date)
        }
    public fun NewsAdapter(context: Context ){
        this.mContext = context}

//    private var mDateListTop:List<NewBean>?=ArrayList()

   @SuppressLint("NotifyDataSetChanged")
   fun setListData(listdate: List<NewBean.StoriesDTO>){
       this.NewsList=listdate
        //用于通知适配器数据集已更改的方法
        notifyDataSetChanged()

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
       val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val news= NewsList?.get(position)
        }

        return viewHolder
    }

    override fun getItemCount() = NewsList?.size as Int

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      var news= NewsList?.get(position)
//        val newsTop= mDateListTop?.get(position)
        if (news != null) {
            holder.newsName.text=news.title
        }
        if (news != null) {
            holder.newsWriter.text=news.hint
        }
        //加载图片
        if (news != null) {
            Glide.with(mContext).load(news.images).into(holder.newsImage)
        }
    }
}