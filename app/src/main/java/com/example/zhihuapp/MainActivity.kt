package com.example.zhihuapp


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.Adapter.BannerAdapter
import com.example.zhihuapp.Adapter.NewsAdapter
import com.example.zhihuapp.Bean.NewBean
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val urls:String="https://news-at.zhihu.com/api/4/news/latest"
    private val newsList=ArrayList<NewBean.StoriesDTO>()
    private val bannersList=ArrayList<NewBean.TopStoriesDTO>()
    var newsAdapter =  NewsAdapter(newsList)
    val banner_adapter= BannerAdapter(bannersList)

    private val mHandler = object :Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {


            super.handleMessage(msg)
            if (msg.what == 100) {
                var mnews: String = msg.obj as String
                //记录主线程收没收到
                Log.d("fas", "-----主线程收到了数据------+$mnews")
                var newBean: NewBean = Gson().fromJson(mnews, NewBean::class.java)
                Log.d("fas", "-----转化数据------"+newBean)
                newsAdapter.setActivity(this@MainActivity)
                newBean.top_stories?.let {banner_adapter.setListData(it)  }
                newBean.stories?.let { newsAdapter.setListData(it)
                }

            }
             true
        }}
//    class MyHandler : Handler(){
//    override fun handleMessage(msg: Message) {
//        super.handleMessage(msg)
//    var respondseData :String=msg.obj.toString()
//    setText(dscodeJson(respondseData))
//    }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
      getNews()
        banner_adapter.setOnItemClickListener2(object : BannerAdapter.TopOnItemClickListener2 {
            override fun toponItemClick2(newsdetail: NewBean.TopStoriesDTO?, position: Int) {
                intent=Intent(this@MainActivity,MainActivity2::class.java)
                intent.putExtra("newsdetail2",newsdetail)
                startActivity(intent)
            }
        })






        newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {

            override fun onItemClick(newsdetail: NewBean.StoriesDTO?, position: Int) {
                intent=Intent(this@MainActivity,MainActivity2::class.java)
                intent.putExtra("newsdetail",newsdetail)
                startActivity(intent)
            }




        })
        val layoutManager=LinearLayoutManager(this)
        val banner_layoutManager=LinearLayoutManager(this)
        banner_layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        val banner_recyclerView=findViewById<RecyclerView>(R.id.banner)
        recyclerView.layoutManager=layoutManager
        banner_recyclerView.layoutManager=banner_layoutManager
        getNews()
        banner_recyclerView.adapter=banner_adapter
        recyclerView.adapter=newsAdapter


    }





fun getNews() {
    Thread {
        val client=OkHttpClient()
        val request=Request.Builder().url(urls).build()
        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    val responseBody= response.body?.string()

                   // Log.d("fas","收到数据"+responseBody)
                    var message:Message = Message.obtain()
                    message.what = 100
                    message.obj =responseBody

                    mHandler.sendMessage(message)


                }
            }

        })



    }.start()
}


}