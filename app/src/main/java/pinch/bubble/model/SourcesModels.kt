package pinch.bubble.model

import com.squareup.moshi.Json

data class Source(@Json(name = "id") val id: Int, @Json(name = "name") val name: String, @Json(name = "image") val imageUrl: String)

data class SourcePost(@Json(name = "age") val age: String, @Json(name = "ids") val ids: List<Int>)