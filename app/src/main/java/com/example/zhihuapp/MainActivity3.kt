package com.example.zhihuapp
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zhihuapp.Bean.NewBean
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import com.example.zhihuapp.Bean.Pinglun
class MainActivity3 : AppCompatActivity() {
    var urls:String= null.toString()
    var pinglun: TextView? =null
    var dianzan: TextView? =null
    private val mHandler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {

            Log.d("fas", "-----主线程收到了数据------+$msg")

            super.handleMessage(msg)
            if (msg.what == 100) {
                var mnews: String = msg.obj as String
                //记录主线程收没收到
                Log.d("fas", "-----主线程收到了数据------+$mnews")
                var newBean: Pinglun = Gson().fromJson(mnews,Pinglun ::class.java)
                if (newBean != null) {
                    pinglun?.text  = "评论数："+ newBean.comments.toString()
                    dianzan?.text  ="点赞数："+newBean.popularity.toString()

                } else {
                    Log.d("fas", "获取数据失败")
                    Log.d("fas", "-----转化数据------"+newBean)
                }
                true
            }
        }}
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main3)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        pinglun=findViewById(R.id.pinglun)
        dianzan=findViewById(R.id.dianzan)
        var webview:WebView=findViewById(R.id.webView2)
           val stories=intent.getSerializableExtra("newsdetail2")as NewBean.TopStoriesDTO
                  val  id=stories.id
        var urls="https://news-at.zhihu.com/api/4/story-extra/"+id
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
                        message.what = 100
                        message.obj =responseBody

                        mHandler.sendMessage(message)


                    }
                }

            })


        }.start()


        webview.loadUrl(stories.url)
    }



}