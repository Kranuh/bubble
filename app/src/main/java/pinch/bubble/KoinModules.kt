package pinch.bubble

import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import pinch.bubble.network.Api
import pinch.bubble.repos.SourcesRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val sourcesRepoModule: Module = applicationContext {
    bean {
        SourcesRepository()
    }
}

val retrofitModule: Module = applicationContext {
    bean {
        val builder = Retrofit.Builder().baseUrl("https://bubblicious.herokuapp.com/")
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.build()
    }
}

val moshiModule: Module = applicationContext {
    bean {
        Moshi.Builder().build()
    }
}

val okHttpClientModule: Module = applicationContext {
    bean {
        val httpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.build()
    }
}

val apiModule: Module = applicationContext {
    bean {
        val retrofit: Retrofit = get()
        val api: Api = retrofit.create(Api::class.java)
        api
    }
}

val picassoModule: Module = applicationContext {
    bean {
        Picasso.Builder(get()).build()
    }
}