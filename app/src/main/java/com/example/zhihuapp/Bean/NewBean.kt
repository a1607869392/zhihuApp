package com.example.zhihuapp.Bean

data class NewBean(var date: String,var stories: List<StoriesDTO>,var  top_stories:List<TopStoriesDTO>) {
   data class StoriesDTO(var image_hue: String,
        var title: String,
        var hint: String,
        var images: List<String>,var url: String,var ga_prefix: String,var type: Int,
                     var id: Int
    ){
       override fun toString(): String {
           return "StoriesDTO(image_hue='$image_hue', title='$title', hint='$hint', images=$images, url='$url', ga_prefix='$ga_prefix', type=$type, id=$id)"
       }
   }
 data   class TopStoriesDTO(
        var image_hue: String,
        var hint: String,
        var url: String,
        var image: String,
        var title: String,
        var ga_prefix: String,var type: Int, var id:Int){
     override fun toString(): String {
         return "TopStoriesDTO(image_hue='$image_hue', hint='$hint', url='$url', image='$image', title='$title', ga_prefix='$ga_prefix', type=$type, id=$id)"
     }
 }

    override fun toString(): String {
        return "NewBean(date='$date', stories=$stories, top_stories=$top_stories)"
    }
}