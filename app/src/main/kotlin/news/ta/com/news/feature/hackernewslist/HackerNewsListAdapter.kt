package news.ta.com.news.feature.hackernewslist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.ta.com.news.R
import news.ta.com.news.databinding.ItemHackerNewsBinding

class HackerNewsListAdapter(viewModel: HackerNewsViewModel) : RecyclerView.Adapter<HackerNewsListAdapter.HackerNewsViewHolder>() {

    var items: List<HackerNewsItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackerNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hacker_news, parent, false)
        return HackerNewsViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HackerNewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HackerNewsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemHackerNewsBinding? = itemView?.let { DataBindingUtil.bind(it) }

        fun bind(item: HackerNewsItem) {
            binding?.item = item
            binding?.listener = View.OnClickListener {
                //viewModel.itemClickEvent.value = item
            }
        }
    }
}