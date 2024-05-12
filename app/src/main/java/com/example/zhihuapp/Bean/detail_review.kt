package com.example.zhihuapp.Bean

import java.io.Serializable

data class detail_review(var comments: List<CommentsDTO>) :Serializable {


    class CommentsDTO (  var author: String,
                         var content: String,
                         var avatar: String,
                         var time: Int,
                         var id: Int,
                         var likes: Int,
                         var reply_to: ReplyToDTO,var replecomments:ReplyToDTO):Serializable{


        class ReplyToDTO (  var content: String,
                            var status: Int,
                            var id: Int,
                            var author: String):Serializable
    }
}