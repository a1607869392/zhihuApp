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

class MainActivity2 : AppCompatActivity() {

            @SuppressLint("SuspiciousIndentation")
            override fun onCreate(savedInstanceState: Bundle?) {
                setContentView(R.layout.activity_main2)
                super.onCreate(savedInstanceState)
                supportActionBar?.hide()
                var webview:WebView=findViewById(R.id.webView)
                val stories=intent.getSerializableExtra("newsdetail")as NewBean.StoriesDTO
                val stories2=intent.getSerializableExtra("newsdetail2")as NewBean.TopStoriesDTO

          Log.d("FAS","收"+stories)
       if (stories2==null){
        webview.loadUrl(stories.url)}
      else {
           webview.loadUrl(stories2.url)
       }
    }
}