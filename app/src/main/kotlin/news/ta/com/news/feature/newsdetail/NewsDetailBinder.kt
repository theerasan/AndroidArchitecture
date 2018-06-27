package news.ta.com.news.feature.newsdetail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.FragmentActivity
import news.ta.com.news.databinding.FragmentNewsDetailsBinding

class NewsDetailBinder(activity: androidx.fragment.app.FragmentActivity, binding: FragmentNewsDetailsBinding) {

    val viewModel = ViewModelProviders.of(activity).get(NewsDetailsViewModel::class.java)
    val router: NewsDetailRouter = NewsDetailRouterImpl(binding.root.context)

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.clickReadMoreEvent.observe(owner, Observer { router.openWebBrowser(it) })
    }
}