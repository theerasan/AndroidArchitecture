package news.ta.com.news.feature.newslist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class NewsListBinder(fragment: androidx.fragment.app.Fragment, binding: FragmentNewsListBinding) {

    val viewModel: NewsListViewModel = ViewModelProviders.of(fragment.activity!!).get(NewsListViewModel::class.java)
    val detailViewModel = ViewModelProviders.of(fragment.activity!!).get(NewsDetailsViewModel::class.java)
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