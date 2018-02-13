package news.ta.com.news.feature.newslist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class NewsListBinder(fragment: Fragment, binding: FragmentNewsListBinding) {

    val viewModel: NewsListViewModel = ViewModelProviders.of(fragment.activity).get(NewsListViewModel::class.java)
    val detailViewModel = ViewModelProviders.of(fragment.activity).get(NewsDetailsViewModel::class.java)
    val view: NewsListView
    val router: NewsListRouter = NewsListRouterImpl(binding.root.context)

    init {
        binding.viewModel = viewModel
        view = NewsListViewImpl(fragment, binding)
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.items.observe(owner, Observer { view.setItems(it) })
        viewModel.showDetailMediator.observe(owner, Observer { router.showDetail(detailViewModel, it) })
        viewModel.gotoDetailMediator.observe(owner, Observer { router.gotoDetail(it) })
    }
}