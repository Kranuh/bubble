package pinch.bubble.repos

import io.reactivex.Single
import org.koin.standalone.inject
import pinch.bubble.base.Repository
import pinch.bubble.model.Topic
import pinch.bubble.network.Api

class TopicsRepository : Repository<List<Topic>> {

    private val api: Api by inject()

    override fun refreshData() {
    }

    override fun observeData(): Single<List<Topic>> {
        return api.getTopics()
    }
}