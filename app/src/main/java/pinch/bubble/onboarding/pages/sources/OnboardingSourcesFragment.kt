package pinch.bubble.onboarding.pages.sources

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_onboarding_sources.*
import org.koin.android.ext.android.inject
import pinch.bubble.R
import pinch.bubble.Resource
import pinch.bubble.Status
import pinch.bubble.model.Source
import pinch.bubble.onboarding.OnboardingActivity
import pinch.bubble.onboarding.pages.OnboardingFragment

class OnboardingSourcesFragment : OnboardingFragment(), Observer<Resource<List<Pair<Source, Boolean>>>> {

    private val picasso: Picasso by inject()
    private val onboardingAdapter: OnboardingSourcesAdapter by lazy {
        OnboardingSourcesAdapter(picasso, { id ->
            storeSelectedName(id)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSources()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_sources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = onboardingAdapter
        recyclerView.itemAnimator = null

        //set up floating action button
        sourcesFloatingActionButton.setOnClickListener {
            (activity as OnboardingActivity).navigateToNextPage()
        }
    }

    private fun observeSources() {
        (activity as OnboardingActivity).getViewModel().getSources().observe(this, this)
    }

    private fun storeSelectedName(id: Int) {
        (activity as OnboardingActivity).getViewModel().setSelectedId(id)
    }

    override fun onChanged(data: Resource<List<Pair<Source, Boolean>>>?) {
        when (data?.status) {
            Status.LOADING -> sourcesProgressBar.visibility = View.VISIBLE
            Status.ERROR -> {
                sourcesProgressBar.visibility = View.GONE
            }
            Status.SUCCESS -> {
                sourcesProgressBar.visibility = View.GONE
                onboardingAdapter.submitList(data.data)
            }
        }
    }
}