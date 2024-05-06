package com.example.zhihuapp.Bean

import java.io.Serializable

 class Pinglun ( val long_comments: Int,
                val popularity: Int,
                val short_comments:Int,
                val comments: Int): Serializable {
     override fun toString(): String {
         return "pi(long_comments=$long_comments, popularity=$popularity, short_comments=$short_comments, comments=$comments)"
     }
 }
