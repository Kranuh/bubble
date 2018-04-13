package pinch.bubble.onboarding.pages

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_onboarding_bubbles.*
import pinch.bubble.R
import pinch.bubble.Status
import pinch.bubble.onboarding.OnboardingActivity

class OnboardingBubbleFragment : OnboardingFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_bubbles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bubblesFloatingActionButton.setOnClickListener {
            (activity as OnboardingActivity).navigateToNextPage()
        }

        (activity as OnboardingActivity).getViewModel().getBubbleLiveData().observe(this, Observer {
            if(it?.status == Status.SUCCESS) {
                it.data?.let {
                    bubbleView.init(it)
                }
            }
        })
    }
}