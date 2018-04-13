package pinch.bubble.onboarding

import android.support.v4.app.FragmentManager
import pinch.bubble.base.BaseFragmentStatePagerAdapter
import pinch.bubble.onboarding.pages.OnboardingFragment
import pinch.bubble.onboarding.pages.OnboardingBubbleFragment
import pinch.bubble.onboarding.pages.sources.OnboardingSourcesFragment
import pinch.bubble.onboarding.pages.OnboardingWelcomeFragment

class OnboardingPagerAdapter(fragmentManager: FragmentManager) : BaseFragmentStatePagerAdapter<OnboardingFragment>(fragmentManager) {
    companion object {
        const val ONBOARDING_PAGE_COUNT = 3
    }

    override fun createFragment(position: Int): OnboardingFragment {
        return when(position) {
            0 -> OnboardingWelcomeFragment()
            1 -> OnboardingSourcesFragment()
            2 -> OnboardingBubbleFragment()
            else -> OnboardingBubbleFragment()
        }
    }

    override fun getCount() = ONBOARDING_PAGE_COUNT
}