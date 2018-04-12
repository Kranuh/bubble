package pinch.bubble.model

import com.squareup.moshi.Json

data class Source(@Json(name = "name") val name: String, @Json(name = "image") val imageUrl: String)