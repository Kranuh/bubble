package pinch.bubble.topics

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pinch.bubble.R

class TopicActivity : AppCompatActivity() {

    private lateinit var viewModel: TopicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        viewModel = ViewModelProviders.of(this).get(TopicViewModel::class.java)
        viewModel.fetchTopicData()

        initialiseFragment()
    }

    private fun initialiseFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.topicContainer, TopicListFragment(), "Initial")
                .commit()
    }

    fun getViewModel() = viewModel

    fun topicSelected(id: Int) {
        // todo, add selected post
        supportFragmentManager.beginTransaction()
                .add(R.id.topicContainer, TopicDetailFragment.newInstance(id), id.toString())
                .addToBackStack(id.toString())
                .commit()
    }

}
