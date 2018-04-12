package pinch.bubble.onboarding.pages.sources

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sources_item.view.*
import pinch.bubble.R
import pinch.bubble.model.Source

class OnboardingSourcesAdapter(private val picasso: Picasso) : ListAdapter<Source, OnboardingSourcesViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Source>() {
            override fun areContentsTheSame(oldItem: Source?, newItem: Source?) = oldItem?.name == newItem?.name
            override fun areItemsTheSame(oldItem: Source?, newItem: Source?) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingSourcesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sources_item, parent, false)
        return OnboardingSourcesViewHolder(picasso, itemView)
    }

    override fun onBindViewHolder(holder: OnboardingSourcesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class OnboardingSourcesViewHolder(private var picasso: Picasso, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val sourceImageView: ImageView = itemView.sourcesImage
    private val sourcesNameTextView: TextView = itemView.sourcesName

    fun bind(source: Source) {
        picasso.load(source.imageUrl).into(sourceImageView)
        sourcesNameTextView.text = source.name
    }
}