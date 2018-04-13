package pinch.bubble.topics

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.topic_detail_item.view.*
import pinch.bubble.R
import pinch.bubble.model.Article

class ArticleAdapter(private val onArticleSelected: (url: String) -> Unit) : ListAdapter<Article, ArticleViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article?, newItem: Article?) = oldItem?.id == newItem?.id

            override fun areItemsTheSame(oldItem: Article?, newItem: Article?) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.topic_detail_item, parent, false)
        return ArticleViewHolder(onArticleSelected, itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ArticleViewHolder(onArticleSelected: (url: String) -> kotlin.Unit, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImage = itemView.articleImage
    private val articleTitle = itemView.articleTitle
    private val articleClickListener = ArticleClickListener(onArticleSelected)

    fun bind(article: Article) {
        articleClickListener.currentUrl = article.articleUrl
        itemView.setOnClickListener(articleClickListener)
        articleTitle.text = article.title
        Picasso.with(itemView.context).load(article.imageUrl).into(articleImage)
    }

    private class ArticleClickListener(private val onArticleSelected: (url: String) -> Unit) : View.OnClickListener {
        var currentUrl = ""

        override fun onClick(v: View?) {
            onArticleSelected(currentUrl)
        }
    }
}