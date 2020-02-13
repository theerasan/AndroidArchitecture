package news.ta.com.news.feature.newslist

import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class NewsListBinder(fragment: Fragment, binding: FragmentNewsListBinding) {

    val viewModel: NewsListViewModel by fragment.activity!!.viewModels()
    val detailViewModel: NewsDetailsViewModel by fragment.activity!!.viewModels()
    val view: NewsListView
    val router: NewsListRouter = NewsListRouterImpl(binding.root.context)

    init {
        binding.viewModel = viewModel
        view = NewsListViewImpl(fragment, binding)
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.items.observe(owner, Observer { view.setItems(it); viewModel.setStatic(it) })
        viewModel.showDetailMediator.observe(owner, Observer { view.setSelectedItem(it!!.id.toLong()); router.showDetail(detailViewModel, it) })
        viewModel.gotoDetailMediator.observe(owner, Observer { router.gotoDetail(it) })
    }
}