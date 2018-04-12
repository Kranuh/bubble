package pinch.bubble.network

import io.reactivex.Completable
import io.reactivex.Single
import pinch.bubble.model.Source
import pinch.bubble.model.SourcePost
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("/api/sources/")
    fun getSources(): Single<List<Source>>

    @POST("/api/sources/")
    fun postSources(@Body sources: SourcePost): Completable
}