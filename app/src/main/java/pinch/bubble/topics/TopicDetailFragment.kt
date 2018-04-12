package pinch.bubble.topics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pinch.bubble.R

class TopicDetailFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.topic_detail_fragment, container, false)
    }
}