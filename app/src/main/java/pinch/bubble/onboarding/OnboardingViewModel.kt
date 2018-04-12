package pinch.bubble.onboarding

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pinch.bubble.Resource
import pinch.bubble.Status
import pinch.bubble.model.Source
import pinch.bubble.repos.SourcesRepository

class OnboardingViewModel : ViewModel(), KoinComponent {

    private val sourcesRepository: SourcesRepository by inject()
    private var sourcesLiveData: MutableLiveData<Resource<List<Source>>> = MutableLiveData()
    private var disposable = Disposables.disposed()

    fun fetchSources() {
        sourcesLiveData.value = Resource(Status.LOADING)
        sourcesRepository.refreshData()

        disposable = sourcesRepository.observeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    sourcesLiveData.value = Resource(Status.SUCCESS, data)
                }, { error ->
                    sourcesLiveData.value = Resource(Status.ERROR, error.message)
                })
    }

    fun getSources(): MutableLiveData<Resource<List<Source>>> = sourcesLiveData

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}