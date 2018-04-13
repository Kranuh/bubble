package pinch.bubble.network

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class UserHeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader("X-Bubble", "volledigrandomuuid").build()
        return chain.proceed(request)
    }
}