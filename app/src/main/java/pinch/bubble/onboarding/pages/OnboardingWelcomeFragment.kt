package pinch.bubble.onboarding.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_onboarding_welcome.*
import pinch.bubble.R
import pinch.bubble.onboarding.OnboardingActivity

class OnboardingWelcomeFragment : OnboardingFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner()

        //set up floating action button
        setupFloatingActionButton()

        (activity as OnboardingActivity).getViewModel().setAge("18")
    }

    private fun setupFloatingActionButton() {
        welcomeFloatingActionButton.setOnClickListener {
            (activity as OnboardingActivity).navigateToNextPage()
        }
    }

    private fun setupSpinner() {
        // create spinner adapter
        val ages = ArrayList<CharSequence>()
        (18..100).forEach {
            ages.add(it.toString())
        }

        val adapter = ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item, ages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        introSpinner.adapter = adapter

        introSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                (activity as OnboardingActivity).getViewModel().setAge("18")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (activity as OnboardingActivity).getViewModel().setAge(ages[position])
            }
        }
    }
}