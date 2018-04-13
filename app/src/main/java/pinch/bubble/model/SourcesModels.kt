package pinch.bubble.model

import com.squareup.moshi.Json

data class Source(@Json(name = "id") val id: Int, @Json(name = "name") val name: String, @Json(name = "image") val imageUrl: String)

data class SourcePost(@Json(name = "age") val age: String, @Json(name = "ids") val ids: List<Int>)

data class Bubble(@Json(name = "name") val name: String, @Json(name = "color") val color: String, @Json(name = "value") val value: Int)