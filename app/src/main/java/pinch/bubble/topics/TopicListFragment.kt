package pinch.bubble.topics

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_topic_list.*
import pinch.bubble.R
import pinch.bubble.Resource
import pinch.bubble.Status
import pinch.bubble.model.Topic

class TopicListFragment : Fragment(), Observer<Resource<List<Topic>>> {

    private val topicAdapter: TopicsAdapter by lazy {
        TopicsAdapter { selectedTopic ->
            selectTopic(selectedTopic)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_topic_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topicRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        topicRecyclerView.adapter = topicAdapter

        (activity as TopicActivity).getViewModel()
                .getTopicLiveData().observe(this, this)
    }

    private fun selectTopic(id: Int) {
        (activity as TopicActivity).topicSelected(id)
    }

    override fun onChanged(data: Resource<List<Topic>>?) {
        when (data?.status) {
            Status.LOADING -> {
                topicProgressBar.visibility = View.VISIBLE
                topicErrorTextView.visibility = View.GONE
            }
            Status.ERROR -> {
                topicProgressBar.visibility = View.GONE
                topicErrorTextView.visibility = View.VISIBLE
            }
            Status.SUCCESS -> {
                topicProgressBar.visibility = View.GONE
                topicErrorTextView.visibility = View.GONE
                topicAdapter.submitList(data.data)
            }
        }
    }
}
