package pinch.bubble.onboarding

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_onboarding.*
import pinch.bubble.R
import pinch.bubble.Status
import pinch.bubble.topics.TopicActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewModel: OnboardingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewModel = ViewModelProviders.of(this).get(OnboardingViewModel::class.java)
        viewModel.fetchSources()

        onboardingViewPager.adapter = OnboardingPagerAdapter(supportFragmentManager)
    }

    fun getViewModel() = viewModel

    fun navigateToNextPage() {
        val currentItem = onboardingViewPager.currentItem
        if (currentItem == OnboardingPagerAdapter.ONBOARDING_PAGE_COUNT - 1) {
            viewModel.postSources().observe(this, Observer { result ->
                if(result?.status == Status.SUCCESS) {
                    val intent = Intent(this, TopicActivity::class.java)
                    startActivity(intent)
                }
            })
        } else {
            onboardingViewPager.currentItem = currentItem + 1
        }
    }
}