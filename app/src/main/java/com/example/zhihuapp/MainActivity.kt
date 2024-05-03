package com.example.zhihuapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhihuapp.Adapter.BannerAdapter
import com.example.zhihuapp.Adapter.NewsAdapter

class MainActivity : AppCompatActivity() {
    private val newsList=ArrayList<News>()
    private val bannersList=ArrayList<Banners>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
       initNews()
        initBanners()
        val layoutManager=LinearLayoutManager(this)
        val banner_layoutManager=LinearLayoutManager(this)
        banner_layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
       val banner_recyclerView=findViewById<RecyclerView>(R.id.banner)
        recyclerView.layoutManager=layoutManager
        banner_recyclerView.layoutManager=banner_layoutManager

       val banner_adapter= BannerAdapter(bannersList)
        val adapter=NewsAdapter(newsList)
        banner_recyclerView.adapter=banner_adapter
        recyclerView.adapter=adapter
    }

    private fun initBanners() { repeat(2){
        bannersList.add(Banners(R.drawable.ic_apple,"jack","发现苹果"))
        bannersList.add(Banners(R.drawable.ic_banna,"Alen","发现巨人"))
    }


    }

    private fun initNews() {
      repeat(2){
          newsList.add(News("apple",R.drawable.ic_apple))
          newsList.add(News("banana",R.drawable.ic_banna))
      }
    }

}