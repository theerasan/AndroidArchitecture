package news.ta.com.news.feature.newslist

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class NewsListBinder(val fragment: Fragment,
                     val binding: FragmentNewsListBinding,
                     val viewModel: NewsListViewModel,
                     private val detailViewModel: NewsDetailsViewModel,
                     private val router: NewsListRouter) {

    var view: NewsListView

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