package pinch.bubble.onboarding

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_onboarding.*
import pinch.bubble.R

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
}