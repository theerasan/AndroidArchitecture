package news.ta.com.news.feature.newsdetail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import news.ta.com.news.databinding.FragmentNewsDetailsBinding

class NewsDetailBinder(binding: FragmentNewsDetailsBinding?,
                       private val router: NewsDetailRouter,
                       private val viewModel: NewsDetailsViewModel) {

    init {
        binding?.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.clickReadMoreEvent.observe(owner, Observer { router.openWebBrowser(it) })
    }
}