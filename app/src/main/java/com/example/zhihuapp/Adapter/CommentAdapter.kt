package com.example.zhihuapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.Bean.NewBean
import com.example.zhihuapp.Bean.detail_review
import com.example.zhihuapp.R

class CommentAdapter(var commentList: List<detail_review.CommentsDTO>?): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
   var commenrtext:TextView=view.findViewById(R.id.context)
   var writer:TextView=view.findViewById(R.id.writer_comment)
   var writerid:TextView=view.findViewById(R.id.writerid_comment)


  }
 fun setListData(listdate: List<detail_review.CommentsDTO>){
  this.commentList=listdate
  //用于通知适配器数据集已更改的方法
  notifyDataSetChanged()}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder{
  val view=LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
   return  ViewHolder(view)
  }

 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  var comments = commentList?.get(position)
  if (comments != null) {
   holder.writer.text = comments.author
   holder.writerid.text = comments.id.toString()
   holder.commenrtext.text = comments.content
  }
  else{
   Log.d("fas","空")
  }

 }
 override fun getItemCount(): Int = commentList?.size as Int

 }