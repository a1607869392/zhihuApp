package com.example.zhihuapp

import com.example.zhihuapp.MainActivity2
import com.example.zhihuapp.MainActivity3
import com.example.zhihuapp.R

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.zhihuapp.Adapter.BannerAdapter
import com.example.zhihuapp.Adapter.NewsAdapter
import com.example.zhihuapp.Bean.NewBean
import com.google.gson.Gson
import kotlin.math.log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {


    private val urls:String="https://news-at.zhihu.com/api/4/news/latest"
    private var urlsdate:String="https://news-at.zhihu.com/api/4/news/before/"
    private val newsList=ArrayList<NewBean.StoriesDTO>()
    private val bannersList=ArrayList<NewBean.TopStoriesDTO>()
    var newsAdapter =  NewsAdapter(newsList)
    private  var currentpage=1
    private  lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var date: TextView? =null
    var datess:Int = 20230425
    var dpage:Int = 7

    val banner_adapter= BannerAdapter(bannersList)

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
                    date?.text  =newBean.date
                    datess= newBean.date.toInt()

                    newBean.stories?.let { newsAdapter.setListData(it) }
                    newBean.top_stories?.let {banner_adapter.setListData(it)  }

                } else {
                    Log.d("fas", "获取数据失败")
                    Log.d("fas", "-----转化数据------"+newBean)
                }

                true
            }
            if (msg.what==0){
                var mnews: String = msg.obj as String
                //记录主线程收没收到
                Log.d("fas", "-----主线程收到了数据------+$mnews")
                var newBean: NewBean = Gson().fromJson(mnews, NewBean::class.java)
                if (newBean != null) {
                    newBean.stories?.let { newsAdapter.addListData(it) }

                } else {
                    Log.d("fas", "获取数据失败")
                    Log.d("fas", "-----转化数据------"+newBean)
                }

                true
            }
        }}


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    date=findViewById(R.id.date)
    supportActionBar?.hide()
    getNews()
    var swipeRefreshLayout:SwipeRefreshLayout=findViewById(R.id.main)


    banner_adapter.setOnItemClickListener2(object : BannerAdapter.TopOnItemClickListener2 {
        override fun toponItemClick2(newsdetail: NewBean.TopStoriesDTO?, position: Int) {
            intent=Intent(this@MainActivity, MainActivity3::class.java)
            intent.putExtra("newsdetail2",newsdetail)
            startActivity(intent)
        }
    })
    newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {

        override fun onItemClick(newsdetail: NewBean.StoriesDTO?, position: Int) {
            intent=Intent(this@MainActivity, MainActivity2::class.java)
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
    banner_recyclerView.adapter=banner_adapter
    recyclerView.adapter=newsAdapter
    swipeRefreshLayout.setOnRefreshListener{
        getNews()
        swipeRefreshLayout.isRefreshing=false
    }
    recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager=recyclerView.layoutManager as LinearLayoutManager
            val lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition()
            if (lastVisibleItemPosition>=newsAdapter.itemCount-dpage&&currentpage<=10){
                currentpage++
                datess--
                dpage+=5
                addnews(datess)
            }

        }

    })
    getNews()
}

    private fun addnews( date:Int) {
        Thread {
          urlsdate= urlsdate+date
            val client=OkHttpClient()
            val request=Request.Builder().url(urlsdate).build()
            client.newCall(request).enqueue(object:Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful){
                        val responseBody= response.body?.string()

                        // Log.d("fas","收到数据"+responseBody)
                        var message:Message = Message.obtain()
                        message.what = 0
                        message.obj =responseBody

                        mHandler.sendMessage(message)
                    }
                }
            })
        }.start()
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
