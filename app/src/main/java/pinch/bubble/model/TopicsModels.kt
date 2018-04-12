package pinch.bubble.model

import com.squareup.moshi.Json

data class Topic(@Json(name = "name") val name: String, @Json(name = "id") val id: Int, @Json(name = "read") val isRead: Boolean,
                 @Json(name = "articles") val articles: List<Article>)

data class Article(@Json(name = "id") val id: Int, @Json(name = "title") val title: String, @Json(name = "body") val body: String,
                   @Json(name = "image") val imageUrl: String, @Json(name = "uri") val articleUrl: String)