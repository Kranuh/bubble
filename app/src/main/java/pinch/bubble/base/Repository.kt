package pinch.bubble.base

import io.reactivex.Single
import org.koin.standalone.KoinComponent

interface Repository<T> : KoinComponent {
    fun refreshData()
    fun observeData(): Single<T>
}