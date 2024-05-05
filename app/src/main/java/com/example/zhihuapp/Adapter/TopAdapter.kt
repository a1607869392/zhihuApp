//package com.example.zhihuapp.Adapter
//
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.zhihuapp.Bean.NewBean
//import com.example.zhihuapp.R
//
//class TopAdapter(var newbean:NewBean ):RecyclerView.Adapter<TopAdapter.ViewHolder>() {
//    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
//        var date:TextView=view.findViewById(R.id.date)
//
//    }
//    fun setListData(newbean: NewBean){
//        this.newbean=newbean
//        //用于通知适配器数据集已更改的方法
//        notifyDataSetChanged()}
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//
//}