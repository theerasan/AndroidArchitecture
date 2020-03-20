package news.ta.com.news.feature.newslist

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import news.ta.com.news.R
import news.ta.com.news.databinding.ItemNewsBinding

class NewsListAdapter(val viewModel: NewsListViewModel, var selectionTracker: SelectionTracker<Long>? = null) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var items: List<NewsItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(selectionTracker, items[position])
    }

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemNewsBinding? = itemView.let { DataBindingUtil.bind(it) }

        fun bind(selectionTracker: SelectionTracker<Long>?, item: NewsItem) {
            binding?.item = item
            binding?.listener = View.OnClickListener {
                viewModel.itemClickEvent.value = item
            }

            binding?.wrapper?.isSelected = selectionTracker?.isSelected(item.id.toLong()) ?: false
            viewModel.selectedCount.set(selectionTracker?.selection?.size().toString())
        }
    }
}

