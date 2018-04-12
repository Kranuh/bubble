package pinch.bubble

import android.app.Application
import org.koin.android.ext.android.startKoin

class BubbleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(sourcesRepoModule, retrofitModule, okHttpClientModule, apiModule, moshiModule, picassoModule, topicsRepoModule))
    }
}