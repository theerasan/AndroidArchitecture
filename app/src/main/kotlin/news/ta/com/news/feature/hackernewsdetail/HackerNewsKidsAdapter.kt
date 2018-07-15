package news.ta.com.news.feature.hackernewsdetail

import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.ta.com.news.R
import news.ta.com.news.databinding.ItemHackerNewsBinding
import news.ta.com.news.feature.hackernewslist.HackerNewsItem
import news.ta.com.news.feature.hackernewslist.HackerNewsViewModel
import news.ta.com.news.feature.hackernewslist.hackernewsitem.HackerNewsItemBinder
import news.ta.com.news.feature.hackernewslist.hackernewsitem.HackerNewsItemViewModel

class HackerNewsKidsAdapter(val viewModel: HackerNewsViewModel, val activity: FragmentActivity) : RecyclerView.Adapter<HackerNewsKidsAdapter.HackerNewsViewHolder>() {

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

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HackerNewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HackerNewsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemHackerNewsBinding? = itemView?.let { DataBindingUtil.bind(it) }

        fun bind(item: HackerNewsItem) {
            val viewModel = HackerNewsItemViewModel(item.id, true)
            val binder = HackerNewsItemBinder(activity, viewModel)
            binding?.listener = View.OnClickListener {
                this@HackerNewsKidsAdapter.viewModel.itemClickEvent.value = item
            }

            binding?.viewModel = viewModel
            binder.bind()
        }
    }
}