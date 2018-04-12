package pinch.bubble.topics

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.topic_detail_item.view.*
import pinch.bubble.R
import pinch.bubble.model.Article

class ArticleAdapter : ListAdapter<Article, ArticleViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article?, newItem: Article?) = oldItem?.id == newItem?.id

            override fun areItemsTheSame(oldItem: Article?, newItem: Article?) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.topic_detail_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleTitle = itemView.articleTitle

    fun bind(article: Article) {
        articleTitle.text = article.title
    }
}