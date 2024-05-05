package com.example.zhihuapp

import android.annotation.SuppressLint
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Util {
   @SuppressLint("SuspiciousIndentation")
   fun doGet(urlstr:String):String{
       var result:String=""
       var connection: HttpURLConnection ?=null
     var  inputStreamReader: InputStreamReader ?=null
     var bufferedReader:BufferedReader?=null
       try {
           val url: URL=URL(urlstr)
           connection=url.openConnection() as HttpURLConnection
           connection.requestMethod="GET"
           connection.connectTimeout=5000
           var  inputStream:InputStream=connection.inputStream
           inputStreamReader= InputStreamReader(inputStream)
           bufferedReader=BufferedReader(inputStreamReader)
           var stringBuilder:StringBuilder=StringBuilder()
           var line:String=""
           while ( bufferedReader.readLine() != null) {
               line=bufferedReader.readLine()
               Log.d("fas","数据"+line)
               stringBuilder.append(line);

           }
           result=stringBuilder.toString()
       } catch (e: Exception) {
      e.printStackTrace()
       } finally {
           if (connection != null) {
               connection.disconnect();
           }

           if (inputStreamReader != null) {
               try {
                   inputStreamReader.close();
               } catch (e: IOException) {
                   e.printStackTrace();
               }
           }

           if (bufferedReader != null) {
               try {
                   bufferedReader.close();
               } catch (e:IOException ) {
                   e.printStackTrace();
               }
           }

       }

       return result;


   }


        }
