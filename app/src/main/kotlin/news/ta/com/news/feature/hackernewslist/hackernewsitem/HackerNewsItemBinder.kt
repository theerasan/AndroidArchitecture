package news.ta.com.news.feature.hackernewslist.hackernewsitem

import android.arch.lifecycle.Observer
import android.support.v4.app.FragmentActivity

class HackerNewsItemBinder(private val fragment: FragmentActivity, val viewModel: HackerNewsItemViewModel) {

    fun bind() {
        viewModel.itemMediator.observe(fragment, Observer { doNothing() })
    }

    private fun doNothing() {
    }
}