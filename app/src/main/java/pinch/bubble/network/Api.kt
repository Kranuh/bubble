package pinch.bubble.network

import io.reactivex.Single
import pinch.bubble.model.Source
import retrofit2.http.GET

interface Api {
    @GET("/api/sources/")
    fun getSources(): Single<List<Source>>
}