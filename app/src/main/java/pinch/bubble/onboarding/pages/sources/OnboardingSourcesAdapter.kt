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

class OnboardingSourcesAdapter(private val picasso: Picasso, private val onItemSelected: (id: Int) -> Unit) : ListAdapter<Pair<Source,
        Boolean>, OnboardingSourcesViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pair<Source, Boolean>>() {
            override fun areContentsTheSame(oldItem: Pair<Source, Boolean>?, newItem: Pair<Source, Boolean>?): Boolean {
                val sourceContentSame = oldItem?.first?.name == newItem?.first?.name
                val selectedStateSame = oldItem?.second == newItem?.second
                return sourceContentSame && selectedStateSame
            }

            override fun areItemsTheSame(oldItem: Pair<Source, Boolean>?, newItem: Pair<Source, Boolean>?) = oldItem?.first == newItem?.first
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingSourcesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sources_item, parent, false)
        return OnboardingSourcesViewHolder(picasso, itemView, onItemSelected)
    }

    override fun onBindViewHolder(holder: OnboardingSourcesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class OnboardingSourcesViewHolder(private var picasso: Picasso, itemView: View, onItemSelected: (id: Int) -> Unit) : RecyclerView
.ViewHolder(itemView) {

    private val sourceImageView: ImageView = itemView.sourcesImage
//    private val sourcesNameTextView: TextView = itemView.sourcesName
    private val sourcesSelectedView = itemView.selectedStateView
    private val clickListener = ClickListener(onItemSelected)

    fun bind(source: Pair<Source, Boolean>) {
        clickListener.currentId = source.first.id
        itemView.setOnClickListener(clickListener)

        picasso.load(source.first.imageUrl).into(sourceImageView)
//        sourcesNameTextView.text = source.first.name
        val selectedVisibility = if (source.second) View.VISIBLE else View.GONE
        sourcesSelectedView.visibility = selectedVisibility
    }

    private class ClickListener(private val onItemSelected: (id: Int) -> Unit) : View.OnClickListener {

        var currentId: Int = -1

        override fun onClick(v: View?) {
            onItemSelected.invoke(currentId)
        }

    }
}