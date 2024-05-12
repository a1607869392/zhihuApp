package com.example.zhihuapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.Adapter.CommentAdapter
import com.example.zhihuapp.Bean.NewBean
import com.example.zhihuapp.Bean.Pinglun
import com.example.zhihuapp.Bean.detail_review
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity4 : AppCompatActivity() {
    private val commentList=ArrayList<detail_review.CommentsDTO>()
    var commentAdapter:CommentAdapter=CommentAdapter(commentList)
    private val mHandler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {

            Log.d("fas", "-----主线程收到了数据------+$msg")

            super.handleMessage(msg)
            if (msg.what == 300) {
                var mnews: String = msg.obj as String
                //记录主线程收没收到
                Log.d("fas", "-----主线程收到了数据------+$mnews")
                var newBean: detail_review = Gson().fromJson(mnews, detail_review ::class.java)
                Log.d("fas","-----主线程转化了数据------+"+newBean)
                if (newBean != null) {
                    newBean.comments?.let { commentAdapter.setListData(it) }
                } else {
                    Log.d("fas", "获取数据失败")
                }
                true
            }
        }}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        val id=intent.getSerializableExtra("commentid")as Int
        var urls:String="https://news-at.zhihu.com/api/4/story/"+id+"/long-comments"
       val recyclerView=findViewById<RecyclerView>(R.id.comment_recyclerview)
        getcomment(urls)
val layoutManager=LinearLayoutManager(this)
 recyclerView.layoutManager=layoutManager
 recyclerView.adapter=commentAdapter
        getcomment(urls)
    }

    private fun getcomment( urls:String) {
        Thread {
            val client= OkHttpClient()
            val request= Request.Builder().url(urls).build()
            client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful){
                        val responseBody= response.body?.string()

                        // Log.d("fas","收到数据"+responseBody)
                        var message: Message = Message.obtain()
                        message.what = 300
                        message.obj =responseBody

                        mHandler.sendMessage(message)


                    }
                }

            })


        }.start()
    }
}