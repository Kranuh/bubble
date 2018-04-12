package pinch.bubble.onboarding

import android.support.v4.app.FragmentManager
import pinch.bubble.base.BaseFragmentStatePagerAdapter
import pinch.bubble.onboarding.pages.OnboardingFragment
import pinch.bubble.onboarding.pages.OnboardingPreferredArticlesFragment
import pinch.bubble.onboarding.pages.sources.OnboardingSourcesFragment
import pinch.bubble.onboarding.pages.OnboardingWelcomeFragment

const val ONBOARDING_PAGE_COUNT = 5
class OnboardingPagerAdapter(fragmentManager: FragmentManager) : BaseFragmentStatePagerAdapter<OnboardingFragment>(fragmentManager) {
    override fun createFragment(position: Int): OnboardingFragment {
        return when(position) {
            0 -> OnboardingWelcomeFragment()
            1 -> OnboardingSourcesFragment()
            else -> OnboardingPreferredArticlesFragment()
        }
    }

    override fun getCount() = ONBOARDING_PAGE_COUNT
}