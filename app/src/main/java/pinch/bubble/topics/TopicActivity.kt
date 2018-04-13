package pinch.bubble.topics

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import pinch.bubble.ArticleFragment
import pinch.bubble.R
import android.support.v4.view.GravityCompat
import android.view.View


class TopicActivity : AppCompatActivity() {

    private lateinit var viewModel: TopicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        viewModel = ViewModelProviders.of(this).get(TopicViewModel::class.java)
        viewModel.fetchTopicData()

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        initialiseFragment()
    }

    private fun initialiseFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.topicContainer, TopicListFragment(), "Initial")
                .commit()
    }

    fun getViewModel() = viewModel

    fun topicSelected(id: Int) {
        supportFragmentManager.beginTransaction()
                .add(R.id.topicContainer, TopicDetailFragment.newInstance(id), id.toString())
                .addToBackStack(id.toString())
                .commit()
    }

    fun articleSelected(url: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse(url)
        startActivity(urlIntent)
    }

}
