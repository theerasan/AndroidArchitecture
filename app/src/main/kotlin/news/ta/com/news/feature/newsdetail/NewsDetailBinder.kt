package news.ta.com.news.feature.newsdetail

import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.fragment.app.FragmentActivity
import news.ta.com.news.databinding.FragmentNewsDetailsBinding

class NewsDetailBinder(activity: FragmentActivity, binding: FragmentNewsDetailsBinding) {

    val viewModel: NewsDetailsViewModel by activity.viewModels()
    val router: NewsDetailRouter = NewsDetailRouterImpl(binding.root.context)

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.clickReadMoreEvent.observe(owner, Observer { router.openWebBrowser(it) })
    }
}