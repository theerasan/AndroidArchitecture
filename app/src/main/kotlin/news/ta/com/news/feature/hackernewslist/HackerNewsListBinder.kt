package news.ta.com.news.feature.hackernewslist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import news.ta.com.news.databinding.FragmentHackerNewsListBinding

class HackerNewsListBinder(fragment: Fragment, val binding: FragmentHackerNewsListBinding) {

    val viewModel = ViewModelProviders.of(fragment.activity!!).get(HackerNewsViewModel::class.java)
    val view: HackersNewsListView = HackersNewsListViewImpl(fragment, binding, viewModel)
    val router: HackerNewsListRouter = HackerNewsListRouterImpl(fragment.context!!)

    fun bindTo(owner: LifecycleOwner) {
        binding.viewModel = viewModel
        viewModel.items.observe(owner, Observer { view.setItem(it) })
        viewModel.itemMediator.observe(owner, Observer { doNothing() })
        viewModel.itemClickEvent.observe(owner, Observer { it?.let { router.gotoDetail(it.id) } })
    }

    private fun doNothing() {}
}