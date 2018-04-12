package pinch.bubble.topics

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_item.view.*
import pinch.bubble.R
import pinch.bubble.model.Topic

class TopicsAdapter(private val onTopicSelected: (id: Int) -> Unit) : ListAdapter<Topic, TopicViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Topic>() {
            override fun areContentsTheSame(oldItem: Topic?, newItem: Topic?) = oldItem?.id == newItem?.id

            override fun areItemsTheSame(oldItem: Topic?, newItem: Topic?) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.topic_item, parent, false)
        return TopicViewHolder(onTopicSelected, itemView)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TopicViewHolder(onTopicSelected: (id: Int) -> Unit, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val topicTitle = itemView.topicTitle
    private val topicClickListener = TopicClickListener(onTopicSelected)

    fun bind(topic: Topic) {
        topicClickListener.currentId = topic.id
        topicTitle.setOnClickListener(topicClickListener)

        topicTitle.text = topic.name
    }

    private class TopicClickListener(private val onTopicSelected: (id: Int) -> Unit) : View.OnClickListener {
        var currentId: Int = -1

        override fun onClick(v: View?) {
            onTopicSelected(currentId)
        }
    }
}