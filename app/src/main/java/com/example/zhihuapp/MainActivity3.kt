package com.example.zhihuapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zhihuapp.Bean.NewBean

class MainActivity3 : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main3)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        var webview:WebView=findViewById(R.id.webView2)

//        val stories=intent.getSerializableExtra("newsdetail")as NewBean.StoriesDTO
           val stories=intent.getSerializableExtra("newsdetail2")as NewBean.TopStoriesDTO

        Log.d("FAS","收"+stories)
//       if (stories2==null){
//        webview.loadUrl(stories.url)}
//      else {
        webview.loadUrl(stories.url)
//       }
    }
}