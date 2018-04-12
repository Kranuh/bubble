package pinch.bubble.repos

import io.reactivex.Completable
import io.reactivex.Single
import org.koin.standalone.inject
import pinch.bubble.base.Repository
import pinch.bubble.model.Source
import pinch.bubble.model.SourcePost
import pinch.bubble.network.Api

class SourcesRepository : Repository<List<Source>> {

    private val api: Api by inject()

    override fun refreshData() {

    }

    override fun observeData(): Single<List<Source>> {
        return api.getSources()
    }

    fun postSource(age: String, selectedSources: List<Int>): Completable {
        return api.postSources(SourcePost(age, selectedSources))
    }

}