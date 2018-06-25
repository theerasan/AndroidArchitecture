package news.ta.com.news.feature.hackernewslist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import news.ta.com.news.databinding.FragmentNewsListBinding

class HackerNewsListBinder(fragment: Fragment, binding: FragmentNewsListBinding) {

    val viewModel = ViewModelProviders.of(fragment.activity!!).get(HackerNewsViewModel::class.java)
    val view: HackersNewsListView = HackersNewsListViewImpl(fragment, binding, viewModel)

    fun bindTo(owner: LifecycleOwner) {
        viewModel.items.observe(owner, Observer { view.setItem(it) })
    }
}