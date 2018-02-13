package news.ta.com.news.feature.newslist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.ta.com.news.R
import news.ta.com.news.databinding.ItemNewsBinding

class NewsListAdapter(val viewModel: NewsListViewModel) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var items: List<NewsItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    inner class NewsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemNewsBinding = DataBindingUtil.bind(itemView)

        fun bind(item: NewsItem) {
            binding.item = item
            binding.listener = View.OnClickListener {
                viewModel.itemClickEvent.value = item
            }
        }
    }
}

