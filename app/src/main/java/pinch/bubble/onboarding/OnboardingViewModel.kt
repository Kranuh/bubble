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

    private var sourcesLiveData: MutableLiveData<Resource<List<Pair<Source, Boolean>>>> = MutableLiveData()
    private var sourcesPostData: MutableLiveData<Resource<Int>> = MutableLiveData()
    private var disposable = Disposables.disposed()

    private var lastSources: List<Source> = ArrayList()
    private val selectedIds = ArrayList<Int>()

    private var selectedAge = "18"

    fun fetchSources() {
        sourcesLiveData.value = Resource(Status.LOADING)
        sourcesRepository.refreshData()

        disposable = sourcesRepository.observeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    setSources(data)
                }, { error ->
                    sourcesLiveData.value = Resource(Status.ERROR, error.message)
                })
    }

    private fun setSources(sources: List<Source>) {
        lastSources = sources

        val sourcesWithState = ArrayList<Pair<Source, Boolean>>()
        for (source in sources) {
            if(selectedIds.contains(source.id)) {
                sourcesWithState.add(source to true)
            } else {
                sourcesWithState.add(source to false)
            }
        }

        sourcesLiveData.value = Resource(Status.SUCCESS, sourcesWithState)
    }

    fun getSources(): MutableLiveData<Resource<List<Pair<Source, Boolean>>>> = sourcesLiveData

    fun setSelectedId(id: Int) {
        // check whether its already stored
        if(selectedIds.contains(id)) {
            selectedIds.remove(id)
        } else {
            selectedIds.add(id)
        }

        setSources(lastSources)
    }

    fun postSources(): MutableLiveData<Resource<Int>> {
        sourcesPostData.value = Resource(Status.LOADING)

        sourcesRepository.postSource(selectedAge, selectedIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sourcesPostData.value = Resource(Status.SUCCESS, 200)
                }, { error ->
                    sourcesPostData.value = Resource(Status.ERROR, error.message)
                })

        return sourcesPostData
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun setAge(charSequence: CharSequence) {
        selectedAge = charSequence.toString()
    }

}