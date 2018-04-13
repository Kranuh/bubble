package pinch.bubble.topics

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_detail_fragment.*
import pinch.bubble.R
import pinch.bubble.Resource
import pinch.bubble.Status
import pinch.bubble.model.Topic

class TopicDetailFragment : Fragment(), Observer<Resource<List<Topic>>> {

    companion object {
        private const val ID_BUNDLE_KEY = "id_bundle_key"

        fun newInstance(id: Int): TopicDetailFragment {
            val args = Bundle().apply {
                putInt(ID_BUNDLE_KEY, id)
            }

            return TopicDetailFragment().apply {
                arguments = args
            }
        }
    }

    private var topicId: Int = -1
    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter { articleUrl ->
            navigateToArticle(articleUrl)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicId = arguments?.getInt(ID_BUNDLE_KEY) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.topic_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topicDetailRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        topicDetailRecyclerView.adapter = articleAdapter

        (activity as TopicActivity).getViewModel().getTopicLiveData().observe(this, this)
    }

    private fun navigateToArticle(url: String) {
        (activity as TopicActivity).articleSelected(url)
    }

    override fun onChanged(data: Resource<List<Topic>>?) {
        when (data?.status) {
            Status.LOADING -> {
                topicDetailProgressBar.visibility = View.VISIBLE
                topicDetailErrorTextView.visibility = View.GONE
            }
            Status.ERROR -> {
                topicDetailProgressBar.visibility = View.GONE
                topicDetailErrorTextView.visibility = View.VISIBLE
            }
            Status.SUCCESS -> {
                topicDetailProgressBar.visibility = View.GONE
                topicDetailErrorTextView.visibility = View.GONE

                data.data?.find { topic ->
                    topic.id == topicId
                }.let { target ->
                    articleAdapter.submitList(target?.articles)
                }
            }
        }
    }
}