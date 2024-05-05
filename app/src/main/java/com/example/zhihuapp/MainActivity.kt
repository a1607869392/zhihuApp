package com.example.zhihuapp


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.Adapter.BannerAdapter
import com.example.zhihuapp.Adapter.NewsAdapter
import com.example.zhihuapp.Bean.NewBean
import com.google.gson.Gson
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val url:String="https://news-at.zhihu.com/api/4/news/latest"
    private val bannersList=ArrayList<Banners>()
    private val newsList=ArrayList<NewBean.StoriesDTO>()
    var newsAdapter =  NewsAdapter(newsList)
    val util: Util = Util()

    private val mHandler = object :Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {

            Log.d("fas", "-----主线程收到了数据------+$msg")
            super.handleMessage(msg)
            if (msg.what == 100) {
                var mnews: String = msg.obj as String
                //记录主线程收没收到
                Log.d("fas", "-----主线程收到了数据------+$mnews")
                var newBean: NewBean = Gson().fromJson(mnews, NewBean::class.java)
                if (newBean != null) {
                    newBean.stories?.let { newsAdapter.setListData(it) }
                } else {
                    Log.d("fas", "获取数据失败")
                }
            }
             true
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
//        getNews()
        initBanners()
        val layoutManager=LinearLayoutManager(this)
        val banner_layoutManager=LinearLayoutManager(this)
        banner_layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        val banner_recyclerView=findViewById<RecyclerView>(R.id.banner)
        recyclerView.layoutManager=layoutManager
        banner_recyclerView.layoutManager=banner_layoutManager
       val banner_adapter= BannerAdapter(bannersList)
        banner_recyclerView.adapter=banner_adapter
       getNews()
        recyclerView.adapter=newsAdapter


    }

    private fun initBanners() { repeat(2){
        bannersList.add(Banners(R.drawable.ic_apple,"jack","发现苹果"))
        bannersList.add(Banners(R.drawable.ic_banna,"Alen","发现巨人"))
    }


    }



fun getNews() {
    Thread {

//请求网络
        var news:String =util.doGet(url)
//传数据给主线程
        var message:Message = Message.obtain()
        message.what = 100
        message.obj =news
        Log.d("fas", "收到"+news)

        mHandler.sendMessage(message)
        Log.d("fas", "发送请求")
    }.start()
}


}
