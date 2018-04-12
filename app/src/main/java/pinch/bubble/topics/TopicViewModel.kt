package pinch.bubble.topics

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pinch.bubble.Resource
import pinch.bubble.Status
import pinch.bubble.model.Topic
import pinch.bubble.repos.TopicsRepository

class TopicViewModel : ViewModel(), KoinComponent {

    private val topicsRepository: TopicsRepository by inject()
    private val topicLiveData: MutableLiveData<Resource<List<Topic>>> = MutableLiveData()

    fun fetchTopicData() {
        topicLiveData.value = Resource(Status.LOADING)

        topicsRepository.observeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ topics ->
                    topicLiveData.value = Resource(Status.SUCCESS, topics)
                }, { error ->
                    topicLiveData.value = Resource(Status.ERROR, error.message)
                })
    }

    fun getTopicLiveData() = topicLiveData
}