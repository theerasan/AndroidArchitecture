package news.ta.com.news.feature.newslist

import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import news.ta.com.news.R
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newslist.NewsListFragment.Companion.HAS_DETAIL

interface NewsListView {
    fun setItems(items: List<NewsItem>?)
    fun setSelectedItem(id: Long)
}

class NewsListViewImpl(fragment: Fragment, val binding: FragmentNewsListBinding) : NewsListView {

    init {
        val hasDetailView = fragment.arguments!!.getBoolean(HAS_DETAIL, false)
        with(binding.list) {
            isNestedScrollingEnabled = false

            val pixelSize = context.resources.getDimensionPixelSize(R.dimen.gap_m)
            addItemDecoration(StartOffsetItemDecoration(pixelSize))
            addItemDecoration(EndOffsetItemDecoration(pixelSize))

            val drawable = ContextCompat.getDrawable(context, R.drawable.decor_m)
            addItemDecoration(DividerItemDecoration(drawable))
            setHasFixedSize(false)

            layoutManager = when (hasDetailView) {
                true -> GridLayoutManager(context, 2)
                else -> LinearLayoutManager(context)
            }

            adapter = NewsListAdapter(binding.viewModel!!)

            if (hasDetailView) {
                val stableIdKeyProvider = StableIdKeyProvider(this)
                val selectionTracker = SelectionTracker.Builder<Long>(
                        "news-selection",
                        this,
                        stableIdKeyProvider,
                        MyItemsLookUp(this, binding.viewModel!!),
                        StorageStrategy.createLongStorage())
                        .build()

                (adapter as NewsListAdapter).selectionTracker = selectionTracker
            }
        }

        binding.viewModel?.hasDetailView = hasDetailView
    }

    override fun setItems(items: List<NewsItem>?) {
        (binding.list.adapter as NewsListAdapter).items = items ?: emptyList()
    }

    override fun setSelectedItem(id: Long) {
        (binding.list.adapter as NewsListAdapter).selectionTracker?.select(id)
    }
}

class MyItemsLookUp(private val recyclerView: RecyclerView, val viewModel: NewsListViewModel) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long?>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)

        if (view != null) {
            val viewHolder = recyclerView.getChildViewHolder(view)

            if (event.action == ACTION_UP) {
                val newsItem = (recyclerView.adapter as NewsListAdapter).items[viewHolder.adapterPosition]
                if (viewModel.itemClickEvent.value != newsItem) {
                    viewModel.itemClickEvent.value = newsItem
                }
            }

            if (viewHolder is NewsListAdapter.NewsViewHolder) {
                return object : ItemDetails<Long?>() {
                    override fun getSelectionKey(): Long? = viewHolder.itemId
                    override fun getPosition(): Int = viewHolder.adapterPosition
                }
            }
        }

        return null
    }
}